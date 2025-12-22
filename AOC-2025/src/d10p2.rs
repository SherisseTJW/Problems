use regex::Regex;
use std::cmp::Reverse;
use std::collections::BinaryHeap;
use std::collections::HashSet;
use std::env;
use std::fs;

// NOTE: Stolen from GPT-san coz idw fight with input anymore
fn parse_line(line: &str) -> (Vec<char>, Vec<Vec<usize>>, Vec<i64>) {
    // Match [....]
    let bracket_re = Regex::new(r"\[(.*?)\]").unwrap();
    // Match (....)
    let paren_re = Regex::new(r"\((.*?)\)").unwrap();
    // Match {...}
    let brace_re = Regex::new(r"\{(.*?)\}").unwrap();

    // ----- Extract pattern inside [ ] -----
    let pattern: Vec<char> = bracket_re
        .captures(line)
        .map(|cap| cap[1].chars().collect())
        .unwrap_or_default();

    // ----- Extract all ( ... ) groups -----
    let mut groups: Vec<Vec<usize>> = Vec::new();
    for cap in paren_re.captures_iter(line) {
        let nums = if cap[1].trim().is_empty() {
            vec![]
        } else {
            cap[1]
                .split(',')
                .map(|s| s.trim().parse::<usize>().unwrap())
                .collect()
        };

        groups.push(nums);
    }

    // ----- Extract { ... } joltage requirements -----
    let joltage: Vec<i64> = brace_re
        .captures(line)
        .map(|cap| {
            cap[1]
                .split(',')
                .map(|s| s.trim().parse::<i64>().unwrap())
                .collect()
        })
        .unwrap_or_default();

    (pattern, groups, joltage)
}

fn transition(
    cur_state: &Vec<i64>,
    action: &Vec<usize>,
    goal_state: &Vec<i64>,
) -> Option<Vec<i64>> {
    let mut nxt_state: Vec<i64> = cur_state.clone();
    for &idx in action {
        nxt_state[idx] += 1;

        if nxt_state[idx] > goal_state[idx] {
            return None;
        }
    }

    Some(nxt_state)
}

fn heuristic(cur_state: &Vec<i64>, goal_state: &Vec<i64>, num_lights: usize) -> i64 {
    let mut h = 0;

    for i in 0..num_lights {
        if cur_state[i] != goal_state[i] {
            h = h.max(goal_state[i] - cur_state[i]);
        }
    }

    h
}

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut sum = 0;
    for line in contents.lines() {
        let (_, actions, goal) = parse_line(line);

        let mut memory: HashSet<Vec<i64>> = HashSet::new();

        let num_lights = goal.len();
        let cur_state = vec![0; num_lights];

        // let mut queue: BinaryHeap<(Vec<i64>, i64)> = BinaryHeap::new();
        // queue.push((cur_state, 0));

        let mut queue: BinaryHeap<(Reverse<i64>, i64, Vec<i64>)> = BinaryHeap::new();
        let h0 = heuristic(&cur_state, &goal, num_lights);
        queue.push((Reverse(h0), 0, cur_state));

        while let Some((Reverse(_f), step, cur_state)) = queue.pop() {
            if cur_state == goal {
                println!("Num actions: {}", step);
                sum += step;
                break;
            }

            if memory.contains(&cur_state) {
                continue;
            }

            memory.insert(cur_state.clone());

            for action in &actions {
                let nxt_state = transition(&cur_state, action, &goal);

                match nxt_state {
                    Some(state) => {
                        if !memory.contains(&state) {
                            let h = heuristic(&state, &goal, num_lights);
                            let f = step + 1 + h;
                            queue.push((Reverse(f), step + 1, state));
                        }
                    }
                    None => {
                        continue;
                    }
                }
            }
        }
    }

    println!("{}", sum);
}
