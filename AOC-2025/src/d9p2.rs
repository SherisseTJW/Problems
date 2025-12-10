use std::collections::HashSet;
use std::env;
use std::fs;

#[derive(Clone, Copy, Eq, PartialEq, Hash)]
struct Coord {
    x: i64,
    y: i64,
}

struct Edge {
    u: Coord,
    v: Coord,
}

struct Rectangle {
    x1: i64,
    x2: i64,
    y1: i64,
    y2: i64,
    area: i64,
}

fn is_point_inside_polygon(p: &Coord, polygon: &Vec<Edge>) -> bool {
    let mut inside = false;

    for edge in polygon {
        let x1 = edge.u.x;
        let y1 = edge.u.y;
        let x2 = edge.v.x;
        let y2 = edge.v.y;

        if (y1 > p.y) != (y2 > p.y) {
            let x_intersect = (x2 - x1) * (p.y - y1) / (y2 - y1) + x1;

            if p.x < x_intersect {
                inside = !inside;
            }
        }
    }

    inside
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

    let mut min_step = 100000;
    let mut polygon: Vec<Edge> = vec![];

    let num_coords = coords.len();
    for i in 0..num_coords {
        let u = coords[i];

        let v = if i + 1 == num_coords {
            coords[0]
        } else {
            coords[i + 1]
        };

        if u.x == v.x {
            min_step = min_step.min((u.y - v.y).abs());
        }

        if u.y == v.y {
            min_step = min_step.min((u.x - v.x).abs());
        }

        polygon.push(Edge { u, v });
    }

    println!("{}", min_step);

    let pt1_ans: i64 = 4769758290;
    let threshold: i64 = pt1_ans / 3;

    let mut rectangles: Vec<Rectangle> = vec![];
    for i in 0..num_coords {
        let fst = coords[i];

        for j in (i + 1)..num_coords {
            let snd = coords[j];

            // Check in polygon
            let x1 = fst.x.min(snd.x);
            let x2 = fst.x.max(snd.x);
            let y1 = fst.y.min(snd.y);
            let y2 = fst.y.max(snd.y);

            let area = ((snd.x - fst.x).abs() + 1) * ((snd.y - fst.y).abs() + 1);
            if area <= threshold {
                rectangles.push(Rectangle {
                    x1,
                    x2,
                    y1,
                    y2,
                    area,
                });
            }
        }
    }

    rectangles.sort_by_key(|a| a.area);
    rectangles.reverse();

    for rectangle in rectangles {
        let x1 = rectangle.x1;
        let x2 = rectangle.x2;
        let y1 = rectangle.y1;
        let y2 = rectangle.y2;

        let mut flag = true;
        for i in (x1..x2).step_by(min_step as usize) {
            for j in (y1..y2).step_by(min_step as usize) {
                let cur_point = Coord { x: i, y: j };
                if !is_point_inside_polygon(&cur_point, &polygon) {
                    flag = false;
                    break;
                }
            }

            if !flag {
                flag = false;
                break;
            }
        }

        let area = ((x1 - x2).abs() + 1) * ((y1 - y2).abs() + 1);
        if flag {
            println!("{}", area);
            break;
        } else {
            println!("Invalid rectangle of size {}", area);
        }
    }
}
