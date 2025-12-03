use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let bank_arr = contents.trim().split("\n");

    let mut sum: i64 = 0;
    for bank in bank_arr {
        let mut fst: i64 = 0;
        let mut snd: i64 = 0;

        let mut batteries = bank.chars();
        for i in 0..bank.len() {
            let battery = batteries.next().unwrap();
            let joltage = battery.to_digit(10).unwrap() as i64;

            if i == bank.len() - 1 {
                if joltage > snd {
                    snd = joltage;
                    continue;
                }

                continue;
            }

            if joltage > fst {
                fst = joltage;
                snd = 0;
                continue;
            }

            if joltage > snd {
                snd = joltage;
                continue;
            }
        }

        let res: i64 = format!("{}{}", fst.to_string(), snd.to_string()).parse::<i64>().unwrap();
        println!("Adding {} to sum", res);
        sum += res;
    }

    println!("{}", sum);
}
