loadI	1032 => rb
loadI	1024 => rc
load	rc => ra
loadI	4 => rc
store rc => 4096
loadI	1028 => rc
store rc => 4100
load	rc => null
store	ra => rb
load 4096 => rb
store rc => 4104
add	rc, rb => null
load 4100 => ra
store rb => 4108
add	rc, ra => rb
load 4104 => rc
store	rb => rc
load 4108 => rc
store rb => 4112
load 4104 => rb
add	null, rc => rb
store rc => 4116
load 4112 => rc
add	null, ra => rc
store	rc => rb
load 4116 => rb
store rc => 4120
store ra => 4124
add	rc, rb => ra
store rb => 4128
load 4124 => rb
load 4120 => rb
add	rb, null => rc
store	rc => ra
load 4128 => ra
store rb => 4132
add	rb, ra => null
store ra => 4136
load 4124 => ra
add	rb, null => rc
load 4132 => rb
store	rc => rb
load 4132 => rb
load 4136 => ra
store rc => 4140
add	rb, ra => rc
load 4140 => rb
store ra => 4144
load 4124 => ra
add	rb, null => ra
store	ra => rc
load 4144 => rc
store ra => 4148
add	rb, rc => ra
load 4124 => rb
store rc => 4152
load 4148 => rc
add	null, rb => rc
store	rc => ra
load 4152 => ra
store rb => 4156
store rc => 4160
add	rb, ra => rc
load 4156 => rb
store ra => 4164
load 4160 => ra
add	null, rb => ra
store	ra => rc
load 4164 => rc
store rb => 4168
store ra => 4172
add	rb, rc => ra
load 4168 => rb
store rc => 4176
load 4172 => rc
add	null, rb => rc
store	rc => ra
store ra => 4180
load 4176 => ra
add	ra, null => null
add	ra, rb => rc
load 4180 => ra
store	rc => ra
load 4180 => ra
store rb => 4184
load 4176 => rb
add	ra, null => rb
store rb => 4188
load 4184 => rb
add	rc, null => ra
load 4188 => rc
store	rb => rc
load 4188 => rc
load	rc => rb
loadI	1036 => rc
load	rc => ra
mult	rb, rc => ra
loadI	1040 => rb
store	rb => rc
loadI	1044 => rb
load	rc => rb
loadI	1048 => rc
load	ra => rc
mult	rb, rc => ra
loadI	1052 => rb
store	rb => rc
loadI	1056 => rb
load	rc => rb
loadI	1060 => rc
load	rc => ra
mult	rb, ra => rc
loadI	1064 => rb
store	rb => rc
loadI	1068 => rb
load	rb => rc
loadI	1072 => rb
load	rb => ra
mult	rb, rc => ra
loadI	1076 => rb
store	rc => rb
output	1040
output	1052
output	1064
output	1076
