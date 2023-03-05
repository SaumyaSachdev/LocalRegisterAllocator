// CS 415 Project #1 - block1.i
// 
// Just a long random computation
// Expects two inputs at locations 1024 and 1028 -
// the first is the initial value used in the computation and 
// the second is the incrementor.
//
// Example usage: sim -i 1024 1 1 < block1.i

	loadI	1032	=> r1
	loadI	1024	=> r10
	add	r1, r10	=> r11
	loadI	1028	=> r13, r14
	output	2000
	add	r13, r1	=> r11
	output	2004
	output	2008