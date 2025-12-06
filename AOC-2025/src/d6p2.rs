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
    let mut num_values = 0;

    for line in contents.lines() {
        for _ in line.chars() {
            eqns.push(vec![]);
        }

        break;
    }

    for line in contents.lines() {
        let mut line_vec: Vec<String> = vec![];

        for char in line.chars() {
            line_vec.push(char.to_string());
            num_values += 1;
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
    //         print!("{}", eqns[i][j]);
    //     }
    //
    //     println!("");
    // }

    let mut values: Vec<Vec<i64>> = vec![];
    let mut operators: Vec<String> = vec![];

    for _ in 0..num_values {
        values.push(vec![]);
    }

    let mut counter = 0;
    for line in eqns {
        let mut cur_value: String = "".to_string();
        let mut flag = true;

        for char in line {
            if char == "+" || char == "*" {
                operators.push(char);
                continue;
            }

            if char == " " {
                continue;
            }

            cur_value.push_str(&char);
            flag = false;
        }

        if !flag {
            values[counter].push(cur_value.parse::<i64>().unwrap());
        }

        else {
            counter += 1;
        }
    }

    // for eqn_val in values {
    //     for val in eqn_val {
    //         print!("{} ", val);
    //     }
    //
    //     println!();
    // }
    //
    // for op in operators {
    //     print!("{} ", op);
    // }

    let mut sum = 0;

    let num_eqns = operators.len();
    for i in 0..num_eqns {
        let mut result = 0;

        if operators[i] == "*" {
            result = 1;
        }

        for j in 0..values[i].len() {
            let val = values[i][j];

            if operators[i] == "+" {
                result += val;
            }

            else {
                result *= val;
            }
        }

        sum += result;
    }

    println!("{}", sum);
}
