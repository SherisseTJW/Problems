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
    let mut width = 0;
    for line in contents.lines() {
        for _ in line.chars() {
            width += 1;
        }

        break;
    }

    let mut beams: Vec<bool> = vec![false; width];

    let mut num_split = 0;
    let mut cur_row = 0;
    for line in contents.lines() {
        let mut i: usize = 0;
        // println!("Checking row {}", cur_row + 1);

        for char in line.chars() {
            if char == 'S' {
                beams[i] = true;
            }

            if char == '^' && beams[i] {
                let mut flag = false;

                if i > 0 {
                    beams[i - 1] = true;
                    beams[i] = false;

                    flag = true;
                }

                if i < width {
                    beams[i + 1] = true;
                    beams[i] = false;

                    flag = true;
                }

                if flag {
                    // println!("Hit a splitter at row {} index {}", cur_row + 1, i + 1);
                    num_split += 1;
                }
            }

            i += 1;
        }

        cur_row += 1;
    }

    // for beam in &beams {
    //     println!("{}", beam);
    // }

    println!("Num beams: {}", num_split);
}
