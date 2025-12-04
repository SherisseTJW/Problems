use std::env;
use std::fs;

fn collect_to_u64(vec: &Vec<u64>) -> u64 {
    let result: String = vec.into_iter().map(|x| x.to_string()).take(12).collect();
    result.parse::<u64>().unwrap()
}

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let bank_arr = contents.trim().split("\n");

    let mut sum: u64 = 0;
    let num_digits: usize = 12;

    for bank in bank_arr {
        let mut result: Vec<u64> = Vec::with_capacity(num_digits);
        let mut batteries = bank.chars();

        let mut max_pops = bank.len() - num_digits;
        for _ in 0..bank.len() {
            let battery = batteries.next().unwrap();
            let joltage = battery.to_digit(10).unwrap() as u64;

            while let Some(&last) = result.last() {
                if joltage > last && max_pops > 0 {
                    max_pops -= 1;
                }
                else {
                    break;
                }
            }

            result.push(joltage);
        }

        sum += collect_to_u64(&result);
    }

    println!("{}", sum);
}
