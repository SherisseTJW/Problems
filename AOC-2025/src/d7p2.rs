use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    let file_path = &args[1];

    println!("In file {}", file_path);

    let contents = fs::read_to_string(file_path).expect("Should have been able to read the file");

    // println!("With text:\n{}", contents);

    // NOTE: Logic below
    let mut grid: Vec<Vec<char>> = vec![];

    for line in contents.lines() {
        let mut cur_row: Vec<char> = vec![];

        for cur_char in line.chars() {
            cur_row.push(cur_char);
        }

        grid.push(cur_row);
    }

    let height = grid.len();
    let width = grid[0].len();

    let mut memo: Vec<Vec<i64>> = vec![vec![-1; width]; height];

    for i in 0..width {
        let cur_char = grid[0][i];

        if cur_char == 'S' {
            let count = dfs(&grid, &mut memo, height, width, 1 as usize, i);
            println!("{}", count);
            break;
        }
    }
}

fn dfs(
    grid: &Vec<Vec<char>>,
    memo: &mut Vec<Vec<i64>>,
    height: usize,
    width: usize,
    cur_row: usize,
    cur_col: usize,
) -> i64 {
    if cur_row >= height - 1 {
        memo[cur_row][cur_col] = 1;
        return 1;
    }

    if memo[cur_row][cur_col] != -1 {
        return memo[cur_row][cur_col];
    }

    let mut paths = 0;

    if grid[cur_row][cur_col] == '^' {
        if cur_col > 0 {
            paths += dfs(&grid, memo, height, width, cur_row + 1, cur_col - 1);
        }

        if cur_col < width {
            paths += dfs(&grid, memo, height, width, cur_row + 1, cur_col + 1);
        }
    } else {
        paths += dfs(&grid, memo, height, width, cur_row + 1, cur_col);
    }

    memo[cur_row][cur_col] = paths;
    paths
}
