use std::fs::File;
use std::io::{BufReader, BufRead, Error};

fn main() -> Result<(), Error> {
    let input = read("../input/day1.txt");
    Ok(())
}

fn read(path: &str) -> Result<Vec<i64>, Error> {
    let mut output = File::create(path)?;
    let input = File::open(path)?;
    let buffered = BufReader::new(input);
    let mut v = Vec::new();
    for line in buffered.lines() {
        let line = line?;
        let n = line   
            .trim() 
            .parse();
        v.push(n);
    }
    Ok(v)
}