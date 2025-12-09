use std::env;
use std::fs;

#[derive(PartialEq, Eq, PartialOrd, Ord, Clone, Copy)]
struct Coord {
    x: i64,
    y: i64
}

// impl Ord for Coords {
//     fn cmp(&self, other: &Self) -> std::cmp::Ordering {
//         if self.x == other.x {
//             return self.y.cmp(&other.y);
//         }
//
//         return self.x.cmp(&other.x);
//     }
// }

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

    coords.sort();

    // for coord in coords {
    //     println!("{}, {}", coord.x, coord.y);
    // }

    let mut largest = 0;

    let num_coords = coords.len();
    for i in 0..num_coords {
        let fst = coords[i];

        for j in (i + 1)..num_coords {
            let snd = coords[j];

            let area = ((snd.x - fst.x).abs() + 1) * ((snd.y - fst.y).abs() + 1);

            if area > largest {
                largest = area;
            }
        }
    }

    println!("{}", largest);
}
