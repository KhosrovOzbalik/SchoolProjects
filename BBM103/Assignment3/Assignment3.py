import sys 
with open(sys.argv[1], "r") as file: commands = [line.split() for line in file.readlines()]
board = [["R1", "N1", "B1", "QU", "KI", "B2", "N2", "R2"],
         ["P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8"],
         ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
         ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
         ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
         ["  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "],
         ["p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8"],
         ["r1", "n1", "b1", "qu", "ki", "b2", "n2", "r2"]]
first_Board = board.copy()
for i in range(len(board)): first_Board[i] = board[i].copy()
# A dictionary to represent letters as numbers.
columns = {"a": 0, "b": 1, "c": 2, "d": 3, "e": 4, "f": 5, "g": 6, "h": 7,
            0: "a", 1: "b", 2: "c", 3: "d", 4: "e", 5: "f", 6: "g", 7: "h"}
w_king, b_king = "e1", "e8"


def find_piece(piece):
    for i in range(len(board)):
        for j in range(len(board[i])):
            if board[i][j] == piece: return j, i


# move() function takes possible areas from showmoves function.
def move(piece, place):
    global w_king,b_king
    x, y = find_piece(piece)
    moves = showmoves(piece)
    if w_king in moves: moves.remove(w_king)
    if b_king in moves: moves.remove(b_king)
    if place in moves:
        if piece[0] == "K": b_king = place
        if piece[0] == "k": w_king = place
        board[y][x] = "  "
        board[8 - int(place[1])][columns[place[0]]] = piece
        return "OK"
    return "FAILED"


# showmoves() function return the possible areas as a list
def showmoves(piece):
    x, y = find_piece(piece)
    if piece[0].upper() == "P":
        if piece[0] == "p" and (board[y - 1][x] == "  " or board[y - 1][x].isupper()):
            return [f"{columns[x]}{9 - y}"]
        if piece[0] == "P" and (board[y + 1][x] == "  " or board[y + 1][x].islower()):
            return [f"{columns[x]}{7 - y}"]
    if piece[0].upper() == "K":
        possible = [[y+1, x], [y+1, x+1], [y+1, x-1], [y, x+1], [y, x-1], [y-1, x+1], [y-1, x-1], [y-1, x]]
        possible = [i for i in possible if not (i[0] < 0 or i[0] > 7 or i[1] < 0 or i[1] > 7 or
                    (piece[0].isupper() and board[i[0]][i[1]][0].isupper()) or (piece[0].islower() and board[i[0]][i[1]][0].islower()))]
        return sorted([f"{columns[i[1]]}{8 - i[0]}" for i in possible])
    if piece[0].upper() == "N":
        possible = [i for i in [[y+1,x+2],[y+1,x-2],[y+2,x+1],[y+2,x-1],[y-1,x+2],[y-1,x-2],[y-2,x+1],[y-2,x-1]] if not (i[0] < 0 or i[0] > 7 or i[1] < 0 or i[1] > 7 or
                    (piece[0].isupper() and board[i[0]][i[1]][0].isupper()) or (piece[0].islower() and board[i[0]][i[1]][0].islower()))]
        possible.extend([i for i in [i for i in [[y+1,x+1],[y+1,x-1],[y-1,x-1],[y-1,x+1]] if not (i[0] < 0 or i[0] > 7 or i[1] < 0 or i[1] > 7)] if board[i[0]][i[1]] == "  "])
        return sorted([f"{columns[i[1]]}{8 - i[0]}" for i in possible])
    if piece[0].upper() == "Q" or piece[0].upper() == "R" or piece[0].upper() == "B":
        possible= []
        if piece[0].upper() == "R" or piece[0].upper() == "Q":
            for i in range(1,8 - y):
                if board[y+i][x] != "  ":
                    if (board[y+i][x][0].isupper() and piece[0].islower()) or (board[y+i][x][0].islower() and piece[0].isupper()): possible.append([y+i,x])
                    break
                possible.append([y+i,x])
            for i in range(1,y+1):
                if board[y-i][x] != "  ":
                    if (board[y-i][x][0].isupper() and piece[0].islower()) or (board[y-i][x][0].islower() and piece[0].isupper()): possible.append([y-i,x])
                    break
                possible.append([y-i,x])
            for i in range(1,8 - x):
                if board[y][x+i] != "  ":
                    if (board[y][x+i][0].isupper() and piece[0].islower()) or (board[y][x+i][0].islower() and piece[0].isupper()): possible.append([y,x+i])
                    break
                possible.append([y,x+i])
            for i in range(1,x+1):
                if board[y][x-i] != "  ":
                    if (board[y][x-i][0].isupper() and piece[0].islower()) or (board[y][x-i][0].islower() and piece[0].isupper()): possible.append([y,x-i])
                    break
                possible.append([y,x-i])
        if piece[0] == "b" or piece[0].upper() == "Q":
            for i in range(1,min(x,y)+1):
                if board[y-i][x-i] != "  ":
                    if board[y-i][x-i][0].isupper(): possible.append([y-i,x-i])
                    break
                possible.append([y-i,x-i])
            for i in range(1,min(y+1,8-x)):
                if board[y-i][x+i] != "  ":
                    if board[y-i][x+i][0].isupper(): possible.append([y-i,x+i])
                    break
                possible.append([y-i,x+i])
        if piece[0] == "B" or piece[0].upper() == "Q":
            for i in range(1,min(8-y,x+1)):
                if board[y+i][x-i] != "  ":
                    if board[y+i][x-i][0].islower(): possible.append([y+i,x-i])
                    break
                possible.append([y+i,x-i])
            for i in range(1,8 - max(x,y)):
                if board[y+i][x+i] != "  ":
                    if board[y+i][x+i][0].islower(): possible.append([y+i,x+i])
                    break
                possible.append([y+i,x+i])
        return sorted([f"{columns[i[1]]}{8 - i[0]}" for i in possible])
    

for i in commands:
    print(">",*i)
    command = i[0]
    if command == "move":
        valid = move(i[1],i[2])
        print(valid)
    elif command == "showmoves":
        poss = showmoves(i[1])
        if w_king in poss: poss.remove(w_king)
        if b_king in poss: poss.remove(b_king)
        if not poss: print("FAILED")
        else: print(*poss)
    elif command == "print":
        for i in board: print(*i)
    elif command == "initialize":
        print("OK")
        for i in range(len(first_Board)):
            board[i] = first_Board[i].copy()
            print(*board[i])
    elif command == "exit": break

"""
2200356012
Hüsrev ÖZBALIK
Assignment 3 
"""
