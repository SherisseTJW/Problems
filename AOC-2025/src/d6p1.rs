use std::collections::HashMap;
use std::env;
use std::fs;
use std::ptr::eq;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut eqns: Vec<Vec<String>> = vec![];
    // let mut eqns: HashMap<i64, Vec<String>> = HashMap::new();

    for line in contents.lines() {
        let cur = line.trim().split(" ");

        let mut line_vec: Vec<String> = vec![];
        let mut num_words = 0;

        for word in cur {
            if word == "" {
                continue;
            }

            line_vec.push(word.trim().to_string());
            num_words += 1;
        }

        for _ in 0..num_words {
            eqns.push(vec![]);
        }

        break;
    }

    for line in contents.lines() {
        let cur = line.trim().split(" ");

        let mut line_vec: Vec<String> = vec![];

        for word in cur {
            if word == "" {
                continue;
            }

            line_vec.push(word.trim().to_string());
        }

        let num_words = line_vec.len();
        for i in 0..num_words {
            eqns[i].push(line_vec[i].clone());
        }
    }

    // NOTE: Just checking parsing is correct
    // let num_lines = eqns.len(); 
    // for i in 0..num_lines {
    //     let num_words = eqns[i].len();
    //     for j in 0..num_words {
    //         println!("{}", eqns[i][j]);
    //     }
    //
    //     println!("");
    // }

    let mut sum = 0;
    for eqn in eqns {
        let op = eqn.last().unwrap();

        let mut result = 0;
        if op == "*" {
            result = 1;
        }
        
        for i in 0..(eqn.len() - 1) {
            if op == "+" {
                result += eqn[i].parse::<i64>().unwrap();
            }

            if op == "*" {
                result *= eqn[i].parse::<i64>().unwrap();
            }
        }

        sum += result;
    }

    println!("{}", sum);
}
