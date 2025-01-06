package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

func loadArrays(filePath string, columnIndex int) ([]int, error) {
	file, err := os.Open(filePath)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var array []int
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		tokens := strings.Split(line, " ")
		if len(tokens) >= columnIndex+1 {
			value, err := strconv.Atoi(tokens[columnIndex])
			if err != nil {
				fmt.Printf("Invalid integer format in line: %s\n", line)
				continue
			}
			array = append(array, value)
		}
	}

	if err := scanner.Err(); err != nil {
		return nil, err
	}

	return array, nil
}

func getDifferences(array1, array2 []int) ([]int, error) {
	if len(array1) != len(array2) {
		return nil, fmt.Errorf("the two arrays must have the same size")
	}

	var differences []int
	for i := range array1 {
		difference := array1[i] - array2[i]
		if difference < 0 {
			difference = -difference
		}
		differences = append(differences, difference)
	}

	return differences, nil
}

func main() {
	filePath := "AOC1input.csv"
	array1, err := loadArrays(filePath, 0)
	if err != nil {
		fmt.Println(err)
		return
	}

	array2, err := loadArrays(filePath, 1)
	if err != nil {
		fmt.Println(err)
		return
	}

	differences, err := getDifferences(array1, array2)
	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Println("Differences contents:", differences)
	total := 0
	for _, difference := range differences {
		total += difference
	}
	fmt.Println("Differences total", total)
}
