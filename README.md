# Job Scheduling Algorithms Comparison

This project compares two greedy algorithms for scheduling jobs on multiple processors to minimize the makespan (total completion time). The implementation evaluates both a standard greedy approach and a sorted greedy-decreasing strategy, demonstrating their effectiveness through empirical analysis.

## Key Components

- **Algorithms**:
  - **Greedy**: Assigns jobs to the least-loaded processor dynamically using a max priority queue.
  - **Greedy-Decreasing**: First sorts jobs in descending order of processing time before applying the greedy strategy.

- **Core Classes**:
  - `Processor`: Manages assigned jobs and tracks total processing time. Uses `JobsList` to store jobs.
  - `Job`: Represents a job with an ID and processing time.
  - `MaxPQ`: A max-heap priority queue implementation for efficiently managing processors based on their load.
  - `Sort`: Implements quicksort to sort jobs by processing time (used in the greedy-decreasing approach).

- **Experiment Framework**:
  - `Comparisons`: Generates test files with varying job counts (100, 250, 500) and computes average makespans for both algorithms across multiple trials.
  - Outputs a comparative analysis of results, highlighting the efficiency gains from sorting jobs.

## Project Highlights

- **Load Balancing**: Utilizes a priority queue to always assign the next job to the processor with the smallest current load.
- **Efficiency**: The greedy-decreasing approach leverages sorting to prioritize larger jobs first, often leading to better load distribution.
- **Empirical Validation**: Tests across generated datasets quantify the performance difference between the two strategies.

## Results Example

The `Comparisons` class outputs formatted results like:
=== Results ===
N = 100:
Greedy Avg Makespan: 452.30
Greedy-Decreasing Avg Makespan: 387.50

N = 250:
Greedy Avg Makespan: 1120.80
Greedy-Decreasing Avg Makespan: 940.20

This demonstrates how sorting jobs can significantly reduce the makespan, especially for larger workloads.

---

🖥️ **Focus**: A study in algorithmic efficiency for resource scheduling, emphasizing practical implementation and comparison of greedy strategies.
