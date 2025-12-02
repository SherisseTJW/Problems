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

            if cur.len() % 2 != 0 {
                continue;
            }

            let mid = cur.len() / 2;

            let fst_half = &cur[0..mid];
            let snd_half = &cur[mid..cur.len()];

            // println!("Fst half: {} and Snd half: {}", fst_half, snd_half);
            
            if fst_half == snd_half {
                sum += i;
            }
        }
    }

    println!("Sum: {}", sum);
}
