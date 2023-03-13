loadI	0 => rb
loadI	4 => rb
loadI	1024 => rc
load	rc => ra
loadI	1028 => rc
store rb => 4096
load	rc => rb
loadI	2000 => rc
store	ra => rc
store rc => 4100
add	ra, rb => rc
load 4100 => ra
store rc => 4104
store rb => 4108
load 4096 => rb
add	ra, null => rc
load 4108 => rb
store	rb => rc
load 4108 => rb
load 4104 => ra
store rc => 4112
add	ra, rb => rc
load 4112 => rb
store rc => 4116
store rc => 4120
load 4096 => rc
add	rb, null => null
load 4120 => rb
store	ra => rb
load 4104 => ra
store rc => 4124
load 4116 => rc
add	null, ra => null
load 4120 => rb
load 4096 => ra
add	rb, ra => rc
load 4116 => rb
store	rb => rc
load 4124 => rb
store rc => 4128
store rc => 4132
load 4116 => rc
add	rb, null => null
store rb => 4136
load 4128 => rb
add	null, ra => rc
load 4136 => rb
store	rb => rc
load 4136 => rb
store rc => 4140
load 4132 => rc
add	null, rb => rc
store rc => 4144
load 4140 => rc
add	null, ra => rb
load 4132 => rc
store	rc => rb
load 4132 => rc
store rb => 4148
load 4144 => rb
add	null, rc => rb
load 4148 => rc
store rb => 4152
add	rc, ra => rb
load 4144 => rc
store	rc => rb
store rc => 4156
load 4144 => rc
load 4152 => rc
add	rc, null => null
store rb => 4160
add	rb, ra => null
load 4160 => rb
store	rc => rb
load 4156 => rc
store rb => 4164
store rb => 4168
load 4152 => rb
add	rc, null => null
load 4164 => rb
store rc => 4172
add	rb, ra => rc
load 4172 => rb
store	rb => rc
load 4172 => rb
store rc => 4176
store rc => 4180
load 4168 => rc
add	null, rb => null
load 4176 => rc
add	rc, ra => rb
load 4168 => rc
store	rc => rb
load 4180 => rc
store rb => 4184
load 4168 => rb
add	rc, null => rb
store rb => 4188
load 4184 => rb
add	null, ra => rb
load 4180 => rc
store	rc => rb
load 4096 => ra
add	rc, ra => rb
load 4188 => rb
store	rb => rc
output	2000
output	2004
output	2008
output	2012
output	2016
output	2020
output	2024
output	2028
output	2032
output	2036
output	2040
output	2044
