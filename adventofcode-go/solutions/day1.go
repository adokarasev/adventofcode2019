package main

import "fmt"
import "github.com/adokarasev/adventofcode2019/adventofcode-go/utils"

func main() {
	input, _ := utils.ReadInts("../input/day1.txt")
	fmt.Printf("1. Total fuel: %v\n", sum(input, calculateFuel))
	fmt.Printf("2. Total fuel: %v\n", sum(input, calculateRequiredFuel))
}

func sum(input []int, f func(int) int) int {
	total := 0
	for _, m := range input {
		total += f(m)
	}
	return total
}

func calculateFuel(m int) int {
	return m/3 - 2
}

func calculateRequiredFuel(m int) int {
	f := m/3 - 2
	if f < 0 {
		return 0
	}
	return f + calculateRequiredFuel(f)
}
