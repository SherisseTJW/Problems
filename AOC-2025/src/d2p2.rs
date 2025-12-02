use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let id_ranges = contents.split(",");
    let mut sum = 0;

    for id_range in id_ranges {
        let mut split = id_range.trim().split("-");

        let fst = split.next().unwrap().trim().parse::<i64>().unwrap();
        let lst = split.next().unwrap().trim().parse::<i64>().unwrap();

        for i in fst..=lst {
            let cur = i.to_string();
            let mid = cur.len() / 2;

            let mut flag = false;
            for j in 1..=mid {
                if cur.len() % j != 0 {
                    continue;
                }

                let expected_match = &cur[0..j];

                let mut start = j;
                let mut end = 2 * j;

                loop {
                    let cur_match = &cur[start..end];

                    if expected_match != cur_match {
                        flag = false;
                        break;
                    }

                    start = end;
                    end = end + j;

                    if end > cur.len() {
                        flag = true;
                        break;
                    }
                }

                if flag {
                    break;
                }
            }

            if flag {
                sum += i;
            }
        }
    }

    println!("Sum: {}", sum);
}
