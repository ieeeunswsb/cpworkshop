from math import sin, cos, asin, sqrt, radians
from datetime import datetime


r = 6378.137

m_lat, m_long = map(float, input().split(","))
m_lat = radians(m_lat)
m_long = radians(m_long)
m_r = float(input())

d_header = input()

people = {}
raw_data = input()


while raw_data:
    data = raw_data.split(",")

    # incredibly ugly piece of code, but it works and is easier than handling
    # the date as a string
    date_str = data[0].split("/")
    date_str_last = date_str[2].split(" ")
    date_obj = datetime(
        year=int(date_str_last[0]), 
        month=int(date_str[0]), 
        day=int(date_str[1]), 
        hour=int(date_str_last[1].split(":")[0]), 
        minute=int(date_str_last[1].split(":")[1])
    )

    if data[-1] not in people.keys():
        people[data[-1]] = {
                'lat' : radians(float(data[1])),
                'long' : radians(float(data[2])),
                'time' : date_obj,
            }
    elif date_obj > people[data[-1]]['time']:
        people[data[-1]] = {
            'lat' : radians(float(data[1])),
            'long' : radians(float(data[2])),
            'time' : date_obj,
        }
    
    try:
        raw_data = input()
    except EOFError:
        break

answers = []
for x in people:
    p = people[x]
    d = 2 * r * asin(
        sqrt(
            pow(sin((m_lat - p['lat']) / 2), 2) + cos(p['lat']) * cos(m_lat) * pow(sin((m_long - p['long']) / 2), 2)
            )
        )
    if d <= m_r:
        answers.append(x)

answers.sort()
print(','.join(answers))
