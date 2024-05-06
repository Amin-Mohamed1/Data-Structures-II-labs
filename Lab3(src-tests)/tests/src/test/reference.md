# Tests expected Answers
## simpleTest
### 0 to 4
- Cost = 6
- Path: 0, 2, 4
## mediumTestHighDensity
### 1 to 10
- Cost = 39
- Path: 1, 4, 11, 10
### 9 to 0
- Cost = ∞
- Path: no path found
## BigTestHighDensity
### 0 to 49
- Cost = 674
- Path: 0, 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49
### 0 to 48
- Cost = 648
- Path: 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48
## testEqualShortestPathsWithNegativeValues
### 0 to 3
- Cost = -5
- Path: 0, 1, 4 || 0, 2, 3, 4
## testEqualShortestPathsWithPositiveValues
### 0 to 3
- Cost = 5
- Path: 0, 2, 3 || 0, 1, 3


belman ford -> negative cycle detection
multiple edges between two nodes
