use regex::Regex;
use std::collections::HashSet;
use std::collections::VecDeque;
use std::env;
use std::fs;

// NOTE: Stolen from GPT-san coz idw fight with input anymore
fn parse_line(line: &str) -> (Vec<char>, Vec<Vec<usize>>) {
    // Match [....]
    let bracket_re = Regex::new(r"\[(.*?)\]").unwrap();
    // Match (....)
    let paren_re = Regex::new(r"\((.*?)\)").unwrap();

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

    (pattern, groups)
}

fn transition(cur_state: &Vec<char>, action: &Vec<usize>) -> Vec<char> {
    let mut nxt_state: Vec<char> = cur_state.clone();
    for &idx in action {
        if cur_state[idx] == '.' {
            nxt_state[idx] = '#';
        } else {
            nxt_state[idx] = '.';
        }
    }

    nxt_state
}

fn heuristic(cur_state: &Vec<char>, goal_state: &Vec<char>, num_lights: usize) -> i64 {
    let mut h = 0;
    for i in 0..num_lights {
        if cur_state[i] != goal_state[i] {
            h += 1;
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
        let (goal, actions) = parse_line(line);

        let mut memory: HashSet<Vec<char>> = HashSet::new();

        let num_lights = goal.len();
        let cur_state = vec!['.'; num_lights];

        let mut queue: VecDeque<(Vec<char>, usize)> = VecDeque::new();

        queue.push_back((cur_state, 0));

        while let Some((cur_state, step)) = queue.pop_front() {
            if cur_state == goal {
                // println!("Num actions: {}", step);
                sum += step;
                break;
            }

            if memory.contains(&cur_state) {
                continue;
            }

            memory.insert(cur_state.clone());

            for action in &actions {
                let nxt_state = transition(&cur_state, action);

                if !memory.contains(&nxt_state) {
                    queue.push_back((nxt_state, step + 1));
                }
            }
        }
    }

    println!("{}", sum);
}
