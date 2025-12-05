use core::num;
use std::env;
use std::fs;

#[derive(PartialEq, Eq, Clone, Copy)]
struct Interval {
    start: i64,
    end: i64,
}

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut intervals: Vec<Interval> = vec![];

    for line in contents.lines() {
        if line.is_empty() {
            break;
        }

        let mut split = line.trim().split("-");
        let fst = split.next().unwrap().trim().parse::<i64>().unwrap();
        let lst = split.next().unwrap().trim().parse::<i64>().unwrap();

        intervals.push(Interval { start: fst, end: lst });
    }

    intervals.sort_by_key(|i| i.start);


    // let mut i = 0;
    // loop {
    //     let num_intervals = intervals.len();
    //     if i >= num_intervals - 1 {
    //         break;
    //     }
    //
    //     let cur_interval = intervals[i];
    //     let nxt_interval = intervals[i + 1];
    //
    //     if nxt_interval.start <= cur_interval.end {
    //         intervals[i].end = nxt_interval.end;
    //         intervals.remove(i + 1);
    //
    //         if i > 0 {
    //             i -= 1;
    //         }
    //     }
    //     else {
    //         i += 1;
    //     }
    // }

    // for interval in intervals {
    //     println!("Interval: {} to {}", interval.start, interval.end);
    // }

    // NOTE: IDK HOW TO START FROM THE MIDDLE LMAO
    let mut fresh_count = 0;

    let mut flag = false;
    for line in contents.lines() {
        if line.is_empty() {
            flag = true;
            continue;
        }

        if flag {
            let cur = line.trim().parse::<i64>().unwrap();

            for interval in &intervals {
                if cur > interval.end {
                    continue;
                }

                if cur <= interval.end && cur >= interval.start {
                    fresh_count += 1;
                }
            }
        }
    }

    println!("Fresh count: {}", fresh_count);

    // let mut fresh_ids: HashSet<i64> = HashSet::new();
    // let mut flag = false;
    //
    // let mut fresh_count = 0;
    // for line in contents.lines() {
    //     if line.is_empty() {
    //         flag = true;
    //         continue;
    //     }
    //
    //     // Checking if fresh
    //     if flag {
    //         let cur = line.trim().parse::<i64>().unwrap();
    //
    //         if fresh_ids.contains(&cur) {
    //             fresh_count += 1;
    //         }
    //     }
    //
    //     // Still reading fresh id ranges
    //     else {
    //         let mut split = line.trim().split("-");
    //         let fst = split.next().unwrap().trim().parse::<i64>().unwrap();
    //         let lst = split.next().unwrap().trim().parse::<i64>().unwrap();
    //
    //         for i in fst..=lst {
    //             fresh_ids.insert(i);
    //         }
    //     }
    // }
    //
    // println!("Fresh Count: {}", fresh_count);
}
