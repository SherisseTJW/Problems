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
    let mut ingredient_ids: Vec<i64> = vec![];

    let mut flag = true;
    for line in contents.lines() {
        if line.is_empty() {
            flag = false;
            continue;
        }

        if flag {
            let mut split = line.trim().split("-");
            let fst = split.next().unwrap().trim().parse::<i64>().unwrap();
            let lst = split.next().unwrap().trim().parse::<i64>().unwrap();

            intervals.push(Interval {
                start: fst,
                end: lst,
            });
        } else {
            let cur = line.trim().parse::<i64>().unwrap();
            ingredient_ids.push(cur);
        }
    }

    intervals.sort_by_key(|i| i.start);

    let mut i = 0;
    loop {
        let num_intervals = intervals.len();
        if i >= num_intervals - 1 {
            break;
        }

        let cur_interval = intervals[i];
        let nxt_interval = intervals[i + 1];

        if nxt_interval.start <= cur_interval.end {
            intervals[i].end = intervals[i].end.max(nxt_interval.end);
            intervals.remove(i + 1);
        } else {
            i += 1;
        }
    }

    let mut num_fresh_ids = 0;
    for interval in &intervals {
        num_fresh_ids += interval.end - interval.start + 1;
    }

    println!("Num Fresh Ids: {}", num_fresh_ids);
}
