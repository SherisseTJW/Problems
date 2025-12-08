use std::env;
use std::fs;

struct UFDS {
    p: Vec<i64>,
    rank: Vec<i64>,
    set_size: Vec<i64>,
    num_sets: i64
}

impl UFDS {
    fn find(&mut self, i: i64) -> i64 {
        let idx: usize = i as usize;

        if self.p[idx] == i {
            return idx as i64;
        }
        else {
            self.p[idx] = self.find(self.p[idx]);
            return self.p[idx];
        }
    }

    fn union(&mut self, i: i64, j: i64) {
        if self.is_same_set(i, j) {
            return;
        }

        self.num_sets -= 1;
        let x = self.find(i) as usize;
        let y = self.find(j) as usize;

        if self.rank[x] > self.rank[y] {
            self.p[x] = y as i64;
            self.set_size[y] += self.set_size[x];
        }
        else {
            self.p[y] = x as i64;
            self.set_size[x] += self.set_size[y];

            if self.rank[x] == self.rank[y] {
                self.rank[y] += 1;
            }
        }
    }

    fn is_same_set(&mut self, i: i64, j: i64) -> bool {
        self.find(i) == self.find(j)
    }

    fn num_disjoint_sets(&self) -> i64 {
        self.num_sets
    }

    fn size_of_set(&mut self, i: i64) -> i64 {
        let root = self.find(i);
        self.set_size[root as usize]
    }
}

#[derive(Clone, Copy)]
struct Node {
    id: i64,
    x: i64,
    y: i64,
    z: i64,
}

struct Edge {
    u: i64,
    v: i64,
    w: f64,
}

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut nodes: Vec<Node> = vec![];
    let mut edges: Vec<Edge> = vec![];

    let mut idx = 0;
    for line in contents.lines() {
        let mut split = line.trim().split(",");

        let x = split.next().unwrap().parse::<i64>().unwrap();
        let y = split.next().unwrap().parse::<i64>().unwrap();
        let z = split.next().unwrap().parse::<i64>().unwrap();

        nodes.push(Node { id: idx, x, y, z });
        idx += 1;
    }

    let num_nodes = nodes.len();
    for i in 0..num_nodes {
        for j in 0..num_nodes {
            if i == j {
                continue;
            }

            let u = nodes[i];
            let v = nodes[j];
            let w = (((u.x - v.x).pow(2) + (u.y - v.y).pow(2) + (u.z - v.z).pow(2)) as f64).sqrt();

            edges.push(Edge { u: u.id, v: v.id, w });
            edges.push(Edge { u: v.id, v: u.id, w });
        }
    }

    edges.sort_by(|a, b| a.w.partial_cmp(&b.w).unwrap());

    let mut p: Vec<i64> = vec![];

    for i in 0..num_nodes {
        p.push(i as i64);
    }

    let mut ufds: UFDS = UFDS { p, rank: vec![0; num_nodes], set_size: vec![1; num_nodes], num_sets: num_nodes as i64 };

    let max_connections = 10;
    let mut num_connections = 0;
    let mut i = 0;

    loop {
        let edge = &edges[i];

        if !ufds.is_same_set(edge.u, edge.v) {
            ufds.union(edge.u, edge.v);
            println!("Unioning {} and {}", edge.u, edge.v);

            num_connections += 1;
        }

        if num_connections >= max_connections {
            break;
        }

        i += 1;
    }

    let mut disjoint_sets: Vec<i64> = vec![-1; 3];
    let mut largest_three: Vec<i64> = vec![-1; 3];
    for i in 0..num_nodes {
        let idx = i as i64;
        let size = ufds.size_of_set(idx);
        let root = ufds.find(idx);

        for j in 0..3 {
            if size > largest_three[j] {
                if disjoint_sets.contains(&root) {
                    continue;
                }

                largest_three[j] = size;
                disjoint_sets[j] = root;
                break;
            }
        }
    }

    let mut result = 1;
    for size in largest_three {
        println!("{}", size);
        result *= size;
    }

    println!("{}", result);
}
