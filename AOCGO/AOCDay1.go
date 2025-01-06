package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
	"strconv"
	"strings"
)

const filePath = "src/AOC1input.csv"

func main() {
	output := loadArrays()
	fmt.Println(output)
}

func loadArrays() string {
	var arrayList1, arrayList2 []int

	file, err := os.Open(filePath)
	if err != nil {
		fmt.Println("Error reading the file:", err)
		return "Failed"
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		line := scanner.Text()
		tokens := strings.Fields(line)
		if len(tokens) == 2 {
			num1, err1 := strconv.Atoi(tokens[0])
			num2, err2 := strconv.Atoi(tokens[1])
			if err1 == nil && err2 == nil {
				arrayList1 = append(arrayList1, num1)
				arrayList2 = append(arrayList2, num2)
			} else {
				fmt.Println("Invalid integer format in line:", line)
			}
		}
	}

	if err := scanner.Err(); err != nil {
		fmt.Println("Error reading the file:", err)
		return "Failed"
	}

	sort.Ints(arrayList1)
	sort.Ints(arrayList2)

	fmt.Println("Sorted Column 1:")
	fmt.Println(arrayList1)

	fmt.Println("Sorted Column 2:")
	fmt.Println(arrayList2)

	differences := getDifferences(arrayList1, arrayList2)
	total := 0
	for _, diff := range differences {
		total += diff
	}
	fmt.Println("Differences contents:", differences)
	fmt.Println("Differences total:", total)

	countIncidence(arrayList1, arrayList2)

	return "Success"
}

func getDifferences(array1, array2 []int) []int {
	fmt.Println("Getting differences")
	differences := make([]int, len(array1))
	for i := range array1 {
		differences[i] = abs(array1[i] - array2[i])
	}
	return differences
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func countIncidence(array1, array2 []int) {
	array2CountMap := make(map[int]int)
	for _, num := range array2 {
		array2CountMap[num]++
	}

	resultMap := make(map[int]int)
	for _, num := range array1 {
		resultMap[num] = num * array2CountMap[num]
	}

	for key, value := range resultMap {
		fmt.Println("Element", key, "* its incidence:", value)
	}

	totalSum := 0
	for _, value := range resultMap {
		totalSum += value
	}
	fmt.Println(totalSum)
}
