use std::env;
use std::fs;

struct UFDS {
    p: Vec<i64>,
    rank: Vec<i64>,
    set_size: Vec<i64>,
    num_sets: i64,
}

impl UFDS {
    fn find(&mut self, i: i64) -> i64 {
        let idx: usize = i as usize;

        if self.p[idx] == i {
            return idx as i64;
        } else {
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
        } else {
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

    ux: i64,
    vx: i64,
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
        for j in (i + 1)..num_nodes {
            let u = nodes[i];
            let v = nodes[j];
            let w = (((u.x - v.x).pow(2) + (u.y - v.y).pow(2) + (u.z - v.z).pow(2)) as f64).sqrt();

            let ux = u.x;
            let vx = v.x;

            // NOTE: Theorectically should be bidrectional but whenever we take, we union
            // Since based on num edges we go through, don't add the other edge
            edges.push(Edge {
                u: u.id,
                v: v.id,
                w,
                ux,
                vx,
            });
        }
    }

    edges.sort_by(|a, b| a.w.partial_cmp(&b.w).unwrap());

    let mut p: Vec<i64> = vec![];

    for i in 0..num_nodes {
        p.push(i as i64);
    }

    let mut ufds: UFDS = UFDS {
        p,
        rank: vec![0; num_nodes],
        set_size: vec![1; num_nodes],
        num_sets: num_nodes as i64,
    };

    let mut i = 0;
    let mut edge1 = 0;
    let mut edge2 = 0;
    loop {
        let edge = &edges[i];
        if !ufds.is_same_set(edge.u, edge.v) {
            ufds.union(edge.u, edge.v);

            edge1 = edge.ux;
            edge2 = edge.vx;

            if ufds.num_sets == 1 {
                break;
            }
        }

        i += 1;
    }

    let result = edge1 * edge2;
    println!("{}", result);
}
