loadI	1024 => rb
loadI	1028 => rc
load	ra => rb
load	ra => rc
loadI	4 => ra
store ra => 4096
loadI	1024 => ra
store	rb => ra
store ra => 4100
add	rb, rc => ra
load 4100 => rb
store rc => 4104
load 4096 => rc
add	rb, null => rc
store	ra => rc
load 4104 => rb
store rc => 4108
add	ra, rb => rc
load 4096 => ra
store rb => 4112
load 4108 => rb
add	null, ra => rb
store	rc => rb
load 4112 => rc
store rb => 4116
store ra => 4120
add	rb, rc => ra
store rc => 4124
load 4120 => rc
load 4116 => rc
add	rc, null => rb
store	ra => rb
load 4124 => ra
store rc => 4128
add	rc, ra => null
store ra => 4132
load 4120 => ra
add	rb, null => rc
load 4128 => rb
store	rb => rc
load 4128 => rb
load 4132 => ra
store rc => 4136
add	rb, ra => rc
load 4136 => rb
store ra => 4140
load 4120 => ra
add	rb, null => ra
store	rc => ra
load 4140 => rb
store ra => 4144
add	rc, rb => ra
load 4120 => rc
store rb => 4148
load 4144 => rb
add	null, rc => rb
store	ra => rb
load 4148 => ra
store rb => 4152
store rc => 4156
add	rb, ra => rc
load 4156 => rb
store ra => 4160
load 4152 => ra
add	null, rb => ra
store	rc => ra
load 4160 => rc
store rb => 4164
store ra => 4168
add	rb, rc => ra
load 4164 => rb
store rc => 4172
load 4168 => rc
add	null, rb => rc
store	ra => rc
load 4172 => ra
store rb => 4176
store rb => 4180
add	rb, ra => null
load 4176 => rc
store ra => 4184
add	ra, rc => rb
load 4180 => ra
store	ra => rb
load 4180 => ra
store rb => 4188
load 4184 => rb
add	ra, null => rb
load 4188 => ra
store rb => 4192
add	ra, rc => rb
load 4192 => ra
store	ra => rb
loadI	1024 => rb
load	rb => ra
store ra => 4196
add	rb, rc => ra
load	ra => rb
load 4176 => rc
store rb => 4200
add	rb, rc => ra
load	ra => rb
load 4200 => rc
store rb => 4204
load 4196 => rb
add	null, rc => null
load	rc => rb
add	rc, rc => rb
load 4204 => rc
mult	rc, rb => ra
loadI	2048 => rb
store	rb => rc
output	2048