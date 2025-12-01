use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path)
        .expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below

    let mut count: i64 = 0;
    let mut curVal: i64 = 50;

    for line in contents.lines() {
        if line.is_empty() {
            continue;
        }

        let mut chars = line.chars();

        let fst: char = chars.next().unwrap();
        let rest: i64 = chars.collect::<String>().parse::<i64>().unwrap();

        // println!("Current value: {} and shifting by {} to {}", curVal, rest, fst);

        match fst {
            'L' => curVal = (curVal - rest).rem_euclid(100),
            'R' => curVal = (curVal + rest) % 100,
            _ => println!("Unexpected character: {}", fst)
        };

        if curVal == 0 {
            count += 1;
        }

        // println!("Resultant value: {}", curVal);
        // println!("{}", line.to_string());
    }

    println!("{}", count);
}

