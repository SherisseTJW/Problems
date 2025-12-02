use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below

    let mut count: i64 = 0;
    let mut cur_val: i64 = 50;

    for line in contents.lines() {
        if line.is_empty() {
            continue;
        }

        let mut chars = line.chars();

        let fst: char = chars.next().unwrap();
        let rest: i64 = chars.collect::<String>().parse::<i64>().unwrap();

        println!(
            "Current value: {} and shifting by {} to {}",
            cur_val, rest, fst
        );

        match fst {
            'L' => {
                let new_val = cur_val - rest;
                if new_val <= 0 && cur_val > 0 {
                    count += 1;
                    println!("Incrementing count")
                }

                count += new_val.abs() / 100;

                println!("Incrementing count by {}", new_val.abs() / 100);

                cur_val = (cur_val - rest).rem_euclid(100);

                println!("New value: {}", new_val);
                println!("Resultant value: {}", cur_val);
            }
            'R' => {
                let new_val = cur_val + rest;
                count += new_val / 100;

                println!("Incrementing count by {}", new_val / 100);

                cur_val = (cur_val + rest) % 100;

                println!("New value: {}", new_val);
                println!("Resultant value: {}", cur_val);
            }
            _ => println!("Unexpected character: {}", fst),
        };

        // println!("Resultant value: {}", cur_val);
        // println!("{}", line.to_string());
    }

    println!("{}", count);
}
