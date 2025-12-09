use std::env;
use std::fs;

#[derive(Clone, Copy)]
struct Coord {
    x: i64,
    y: i64
}

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut coords: Vec<Coord> = vec![];

    for line in contents.lines() {
        let mut tmp = line.trim().split(",");

        let x = tmp.next().unwrap().parse::<i64>().unwrap();
        let y = tmp.next().unwrap().parse::<i64>().unwrap();

        coords.push(Coord { x, y });
    }


}
