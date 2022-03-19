harita = input("Please enter feeding map as a list:\n")
moves = input("Please enter direction of movements as a list:\n")
point, die = 0, 0
# Converting maps format string to list
harita = harita.strip("[]").replace("'", "").replace("[", "").split(",")
harita = "".join(harita).split("]")
print("Your board is:")
for i in range(len(harita)):
    harita[i] = harita[i].strip()
    print(harita[i])
# Converting moves format string to list
moves = moves.strip("[]").replace("'", "").replace(" ", "").split(",")
position_y, position_x = 0, 0


def find_rabbit():
    global position_y, position_x
    for y in range(len(harita)):
        for x in range(0, len(harita[y]), 2):
            if harita[y][x] == "*":
                position_x, position_y = x, y
                return


find_rabbit()


def possibilities(y, x):
    global point, position_y, position_x, harita, die
    try:
        if harita[y][x] == "W" or x < 0 or y < 0:
            return
    except: return
    harita[y], harita[position_y] = list(harita[y]), list(harita[position_y])
    if harita[y][x] == "C": point += 10
    if harita[y][x] == "A": point += 5
    if harita[y][x] == "M": point -= 5
    if harita[y][x] == "P":
        print("You Died.")
        die = 1
    harita[position_y][position_x] = "X"
    harita[y][x] = "*"
    harita[y] = "".join(harita[y])
    harita[position_y] = "".join(harita[position_y])
    position_x, position_y = x, y


for move in moves:
    if move == "U": possibilities(position_y - 1, position_x)
    if move == "D": possibilities(position_y + 1, position_x)
    if move == "L": possibilities(position_y, position_x - 2)
    if move == "R": possibilities(position_y, position_x + 2)
    if die == 1: break
print("Your output should be like this:")
for i in range(len(harita)): print(harita[i])
print(f"Your score: {point}")

"""
[['X','X','X','X','X']['X','X','X','X','X']['X','X','X','X','X']['X','X','X','X','X']['X','*','X','X','X']]

['U','U','U','R','R']

"""
