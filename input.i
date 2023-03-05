// CS 415 Project #1 - block1.i
// 
// Just a long random computation
// Expects two inputs at locations 1024 and 1028 -
// the first is the initial value used in the computation and 
// the second is the incrementor.
//
// Example usage: sim -i 1024 1 1 < block1.i


	loadI   1028  => r1 
	load	r1  => r2         
	mult	r1, r2   => r3 
	loadI	5  => r4 
	sub	r4, r2   => r5 
	loadI	8  => r6 
	mult	r5, r6   => r7 
	sub	r7, r3   => r8 
	store	r8 => r1 