use std::collections::{HashMap, VecDeque};
use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below

    let mut num_paths: i64 = 0;
    let mut al: HashMap<String, Vec<String>> = HashMap::new();

    for line in contents.lines() {
        let mut tmp = line.split(": ");
        let u = tmp.next().unwrap().to_string();

        let collected_outputs: String = tmp.collect();
        let outputs: Vec<String> = collected_outputs
            .split(" ")
            .map(|s| s.to_string())
            .collect();

        al.insert(u, outputs);
    }

    al.insert("out".to_string(), vec![]);

    let mut stack: VecDeque<(String, bool, bool, bool)> = VecDeque::new();
    stack.push_back(("svr".to_string(), false, false, false));

    let mut memo: HashMap<(String, bool, bool), i64> = HashMap::new();

    let goal = "out".to_string();
    while let Some((u, mut dac, mut fft, expanded)) = stack.pop_back() {
        if u == "dac" {
            dac = true;
        }

        if u == "fft" {
            fft = true;
        }

        let state = (u.clone(), dac, fft);
        if memo.contains_key(&state) {
            continue;
        }

        if expanded {
            if u == goal {
                if dac && fft {
                    memo.insert(state, 1);
                }
                else {
                    memo.insert(state, 0);
                }

                continue;
            }

            let mut sum = 0;
            let neighbors = al.get(&u).unwrap();
            for v in neighbors {
                let nd = dac || v == "dac";
                let nf = fft || v == "fft";
                sum += memo[&(v.clone(), nd, nf)];
            }

            memo.insert(state, sum);
        } 
        else {
            stack.push_back((u.clone(), dac, fft, true));

            let neighbors = al.get(&u).unwrap();
            for v in neighbors {
                let nd = dac || v == "dac";
                let nf = fft || v == "fft";

                if !memo.contains_key(&(v.clone(), nd, nf)) {
                    stack.push_back((v.clone(), nd, nf, false));
                }
            }
        }
    }

    let num_paths = memo[&("svr".to_string(), false, false)];
    println!("{}", num_paths);
}
