loadI	4000 => rb
loadI	4 => rc
loadI	1 => ra
store ra => 4096
load	rb => ra
store ra => 4100
add	ra, rc => rb
load	rb => ra
store ra => 4104
add	ra, rc => rb
load	rb => ra
store ra => 4108
add	rb, rc => ra
load	ra => rb
store rb => 4112
add	rb, rc => ra
load	ra => rb
store rb => 4116
add	ra, rc => rb
load	rb => ra
store ra => 4120
add	ra, rc => rb
load	rb => ra
store ra => 4124
add	ra, rc => rb
load	rb => ra
store ra => 4128
add	rb, rc => ra
load	ra => rb
store rb => 4132
add	rb, rc => ra
load	ra => rb
store rb => 4136
add	rc, rb => ra
loadI	0 => rb
load 4100 => rb
load 4104 => rc
add	rb, rc => ra
store ra => 4140
store ra => 4144
load 4096 => ra
mult	null, rc => null
load 4100 => rb
store rc => 4148
load 4136 => rc
sub	null, rb => ra
load 4108 => rb
load 4140 => rc
store ra => 4152
add	rc, rb => ra
load 4144 => rc
store ra => 4156
mult	rc, rb => ra
load 4152 => rc
store rb => 4160
load 4132 => rb
add	rc, null => rb
load 4112 => rc
store rb => 4164
load 4156 => rb
add	null, rc => rb
store rb => 4168
mult	ra, rc => rb
store rc => 4172
load 4164 => rc
load 4128 => rc
sub	null, rc => ra
store rc => 4176
store rc => 4180
load 4116 => rc
load 4168 => rc
add	rc, null => null
load 4116 => rc
store rb => 4184
mult	rb, rc => null
store rc => 4188
load 4124 => rc
add	rb, null => ra
load 4180 => rb
store rc => 4192
load 4120 => rc
add	rb, null => null
load 4184 => rb
store rc => 4196
load 4120 => rc
mult	rb, null => null
load 4120 => rb
sub	ra, rb => rc
load 4192 => rb
load 4124 => rc
store ra => 4200
add	rb, rc => ra
load 4196 => rb
load 4124 => rc
store ra => 4204
mult	rb, rc => ra
load 4200 => rb
load 4188 => rc
store ra => 4208
add	rb, rc => ra
loadI	16 => rb
load 4208 => rc
store ra => 4212
rshift	rc, rb => ra
load 4204 => rc
store rb => 4216
load 4176 => rb
add	rc, null => null
load 4176 => rc
mult	ra, rc => rb
load 4172 => ra
store rc => 4220
load 4212 => rc
sub	null, ra => null
load 4216 => rc
store ra => 4224
load 4132 => ra
add	rc, null => null
load 4132 => ra
mult	rb, ra => rc
load 4160 => rb
load 4220 => ra
store rc => 4228
add	ra, rb => rc
load 4224 => rb
store ra => 4232
load 4136 => ra
add	rb, null => null
load 4228 => rb
load 4136 => ra
store rc => 4236
mult	rb, ra => rc
load 4148 => rb
load 4236 => ra
store rc => 4240
sub	ra, rb => rc
loadI	1024 => rb
load 4232 => rb
store	rb => ra
loadI	1028 => rb
load 4240 => ra
store	ra => rb
loadI	1032 => rb
store	rc => rb
output	1024
output	1028
output	1032
