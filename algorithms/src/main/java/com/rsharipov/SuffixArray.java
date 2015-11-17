package com.rsharipov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SuffixArray {

    private final int[] ranks;
    private List<int[]> storedRanks;
    private int[] indexes;
    private final String input;
    private List<Integer>[] countingSortBuckets;
    private RankPairs[] pairs;

    private static class RankPairs implements Comparable<RankPairs>
    {
        private int first;
        private int second;
        private int suffixIndex;

        public RankPairs(int first, int second, int suffixIndex)
        {
            this.first = first;
            this.second = second;
            this.suffixIndex = suffixIndex;
        }

        boolean hasEqualValues(RankPairs o) {
            return first == o.first && second == o.second;
        }

        @Override
        public String toString() {
            return "[" + first + ", " + second + ", " + suffixIndex + "]";
        }

        @Override
        public int compareTo(RankPairs o) {
            int result = Integer.compare(first, o.first);
            if (result != 0) return result;
            return Integer.compare(second, o.second);
        }
    }

    /**
     * Builds the suffix array for @input in O(N * log(N))
     * @param input 
     */
    @SuppressWarnings("unchecked")
    public SuffixArray(String input, int minChar, int maxChar) {        
        countingSortBuckets = new List[input.length()];
        for (int i = 0; i < input.length(); ++i) {
            countingSortBuckets[i] = new LinkedList<>();
        }
        this.storedRanks = new ArrayList<int[]>();
        this.input = input;
        this.pairs = new RankPairs[input.length()];
        for (int i = 0; i < input.length(); ++i) {
            pairs[i] = new RankPairs(0, 0, 0);
        }
        this.ranks = calculateRanks(input, minChar, maxChar);        
    }

    private void countingSort(int[] ranks, int[] suffixIndexes) {
        for (int i = 0; i < countingSortBuckets.length; ++i) {
            countingSortBuckets[i].clear();
        }
        for (int i = 0; i < suffixIndexes.length; ++i) {
            countingSortBuckets[ranks[suffixIndexes[i]]].add(suffixIndexes[i]);
        }
        int index = 0;
        for (List<Integer> bucket : countingSortBuckets) {
            if (bucket.isEmpty()) break;
            for (int v : bucket) {
                suffixIndexes[index++] = v;
            }
        }
    }

    private static int[] calculateFirstCharRanks(String input, int minChar, int maxChar) {
        int[] counts = new int[maxChar - minChar + 1];
        for (int i = 0; i < input.length(); ++i) {
            ++counts[input.charAt(i) - minChar];
        }
        int charRank = 0;
        int[] charRanks = new int[maxChar - minChar + 1];
        for (int i = 0; i < charRanks.length; ++i) {
            if (counts[i] > 0) {
                charRanks[i] = charRank++;
            }
        }
        int[] ranks = new int[input.length()];
        for (int i = 0; i < ranks.length; ++i) {
            ranks[i] = charRanks[input.charAt(i) - minChar];
        }
        return ranks;
    }

    private int[] calculateRanks(String input, int minChar, int maxChar) {
        int length = input.length();                
        if (length == 1) return new int[] { 0 };
        int ranks[][] = new int[2][];
        int currentRanks = 0;
        ranks[0] = calculateFirstCharRanks(input, minChar, maxChar);        
        ranks[1] = new int[length];
        int suffixIndexes[][] = new int[2][];
        suffixIndexes[0] = new int[length];
        suffixIndexes[1] = new int[length];
        int currentSI = 0;
        int maxRank = -1;
        for (int halfSuffixLength = 1; halfSuffixLength < length; halfSuffixLength *= 2) {            
            if (halfSuffixLength != 1) {
                int shift = length - halfSuffixLength;
                for (int i = 0; i < halfSuffixLength; ++i) {
                    suffixIndexes[1 - currentSI][i] = i + shift;
                }
                int index = halfSuffixLength;
                for (int i = 0; i < length; ++i) {
                    if (suffixIndexes[currentSI][i] >= halfSuffixLength) {
                        suffixIndexes[1 - currentSI][index++] = suffixIndexes[currentSI][i] - halfSuffixLength;
                    }
                }
                currentSI = 1 - currentSI;
                countingSort(ranks[currentRanks], suffixIndexes[currentSI]);
                storedRanks.add(Arrays.copyOf(ranks[currentRanks], ranks[currentRanks].length));
            }
            else {
                for (int i = 0; i < length; ++i) {
                    pairs[i] = new RankPairs(ranks[currentRanks][i], i + halfSuffixLength < length ? ranks[currentRanks][i + halfSuffixLength] : -1, i);
                }
                Arrays.sort(pairs);
                for (int i = 0; i < length; ++i) {
                    suffixIndexes[currentSI][i] = pairs[i].suffixIndex;
                }
            }
            int rank = 0;
            int prevLowerRank = ranks[currentRanks][suffixIndexes[currentSI][0]];
            int prevHigherRank = suffixIndexes[currentSI][0] + 
                halfSuffixLength < length ? 
                    ranks[currentRanks][suffixIndexes[currentSI][0] + halfSuffixLength] : -1;
            for (int i = 0; i < length; ++i) {
                if (i != 0) {
                    int currentLowerRank = ranks[currentRanks][suffixIndexes[currentSI][i]];
                    int currentHigherRank = suffixIndexes[currentSI][i] + halfSuffixLength < length ? 
                            ranks[currentRanks][suffixIndexes[currentSI][i] + halfSuffixLength] : -1;
                    if (prevLowerRank != currentLowerRank ||
                        prevHigherRank != currentHigherRank) {
                        ++rank;
                        prevLowerRank = currentLowerRank;
                        prevHigherRank = currentHigherRank;
                    }
                }
                ranks[1 - currentRanks][suffixIndexes[currentSI][i]] = rank;
            }
            maxRank = rank;
            currentRanks = 1 - currentRanks;
            storedRanks.add(Arrays.copyOf(ranks[currentRanks], ranks[currentRanks].length));
        }
        return ranks[currentRanks];        
    }

    private static int[] indexes(int[] ranks) {
        int[] indexes = new int[ranks.length];
        for (int i = 0; i < indexes.length; ++i) {
            indexes[ranks[i]] = i;
        } 
        return indexes;
    }

    /**
     * @param index the starting index of the suffix
     * @return how many suffixes are smaller than the one starting at 
     * @index in the input
     */
    public int rank(int index) {
        return ranks[index];
    }

    /**
     * @param rank the number from 0 to input.length() - 1 inclusively
     * @return starting index of the suffix with the rank @rank
     */
    public int suffix(int rank) {
        if (indexes == null) {
            indexes = indexes(ranks);
        }
        return indexes[rank];
    }

    public int length() {
        return input.length();
    }
    
    public int lcp(int first, int second)
	{
		if (first >= length() || second >= length()) return 0;
		for (int i = storedRanks.size() - 1; i >= 0; --i)
		{
			if (storedRanks.get(i)[first] == storedRanks.get(i)[second])
			{
				return (1 << i) + lcp(first + (1 << i), second + (1 << i));
			}
		}
		return 0;
	}
}
