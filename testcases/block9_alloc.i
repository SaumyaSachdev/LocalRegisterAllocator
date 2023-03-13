loadI 1024 => rb
loadI 1028 => rc
loadI 0 => ra
loadI 4 => ra
store ra => 4096
load rb => ra
load rb => rc
loadI 2000 => rb
store rb => 4100
add ra, rb => rc
add rc, ra => rb
store ra => 4104
add rb, rc => ra
store rc => 4108
add ra, rb => rc
store rb => 4112
add rc, ra => rb
store ra => 4116
add rb, rc => ra
store rc => 4120
add ra, rb => rc
store rb => 4124
add rc, ra => rb
store ra => 4128
add rb, rc => ra
store rc => 4132
add ra, rb => rc
store rc => 4136
load 4100 => rc
load 4104 => rc
store rc => null
load 4100 => rc
store ra => 4140
store rb => 4144
load 4096 => rb
add rc, null => ra
load 4108 => rb
store rb => ra
load 4096 => ra
add rb, ra => rc
load 4112 => rb
store rb => rc
add rc, ra => rb
load 4116 => rc
store rc => rb
add rb, ra => rc
load 4120 => rb
store rb => rc
add rc, ra => rb
load 4124 => rc
store rc => rb
add rb, ra => rc
load 4128 => rb
store rb => rc
add rc, ra => rb
load 4132 => rc
store rc => rb
add rb, ra => rc
load 4144 => rb
store rb => rc
add rc, ra => rb
load 4140 => rc
store rc => rb
load 4096 => ra
add rc, ra => rb
load 4136 => rc
store rc => rb
output 2000
output 2004
output 2008
output 2012
output 2016
output 2020
output 2024
output 2028
output 2032
output 2036
output 2040
