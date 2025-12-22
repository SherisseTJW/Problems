use std::collections::{HashMap, VecDeque};
use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below

    let mut num_paths: i32 = 0;
    let mut al: HashMap<String, Vec<String>> = HashMap::new();
    
    for line in contents.lines() {
        println!("{}", line);
        let mut tmp = line.split(": ");
        let u = tmp.next().unwrap().to_string();

        let collected_outputs: String = tmp.collect();
        let outputs: Vec<String> = collected_outputs.split(" ").map(|s| s.to_string()).collect();

        al.insert(u, outputs);

        // println!("Key: {}", u);
        //
        // for out in outputs {
        //     println!("{}", out);
        // }
    }

    al.insert("out".to_string(), vec![]);

    let mut stack: VecDeque<String> = VecDeque::new();
    stack.push_back("you".to_string());

    let goal = "out".to_string();
    while let Some(u) = stack.pop_back() {
        if u == goal {
            num_paths += 1;
        }

        let outputs = al.get(&u).unwrap();

        for out in outputs {
            stack.push_back(out.clone());
        }
    }

    println!("{}", num_paths);
}
