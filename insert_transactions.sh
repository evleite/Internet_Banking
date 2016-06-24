#!/bin/bash

for i in {1..100}
do
    echo "insert into transactions values (null, 1000, 'RO01TEST8712243875460969', '2016-06-24 21:09:44', 'performance test', 'RO01TEST8712243875460969', 3, 2, 7, 0);" | mysql -uroot -pM2xim_29 eBank9;
done
