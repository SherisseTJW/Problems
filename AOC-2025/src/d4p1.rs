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
    let rows = contents.trim().split("\n");

    for row in rows {
        let cur = row.chars().collect();
        grid.push(cur);
    }

    let num_rows = grid.len();
    let num_cols = grid[0].len();

    let mut total = 0;
    for i in 0..num_rows {
        for j in 0..num_cols {
            if grid[i][j] == '.' {
                continue;
            }

            let mut count = 0;
            if i > 0 {
                // Top
                if grid[i - 1][j] == '@' {
                    count += 1;
                }

                // Top left
                if j > 0 && grid[i - 1][j - 1] == '@' {
                    count += 1;
                }

                // Top right
                if j < num_cols - 1 && grid[i - 1][j + 1] == '@' {
                    count += 1;
                } 
            }

            if i < num_rows - 1 {
                // Bottom
                if grid[i + 1][j] == '@' {
                    count += 1;
                }

                // Bottom left
                if j > 0 && grid[i + 1][j - 1] == '@' {
                    count += 1;
                }

                // Bottom right
                if j < num_cols - 1 && grid[i + 1][j + 1] == '@' {
                    count += 1;
                } 
            }

            // Left
            if j > 0 && grid[i][j - 1] == '@' {
                count += 1;
            }

            // Right
            if j < num_cols - 1 && grid[i][j + 1] == '@' {
                count += 1;
            }

            if count < 4 {
                // println!("Found at ({}, {})", i, j);
                total += 1;
            }
        }
    }

    println!("Total: {}", total);
}
