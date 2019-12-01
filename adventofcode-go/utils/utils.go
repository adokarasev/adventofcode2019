package utils

import (
	"bufio"
	"os"
	"strconv"
)

/*ReadInts reads ints from file*/
func ReadInts(fileName string) (nums []int, err error) {
	f, err := os.Open(fileName)
	scanner := bufio.NewScanner(f)
	scanner.Split(bufio.ScanLines)
	var result []int
	for scanner.Scan() {
		x, err := strconv.Atoi(scanner.Text())
		if err != nil {
			return result, err
		}
		result = append(result, x)
	}
	return result, scanner.Err()
}
