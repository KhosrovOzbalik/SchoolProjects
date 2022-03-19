import sys


class Empty(Exception):
    def __init__(self, argument):
        self.argument = argument


try:
    if len(sys.argv) != 5:
        raise IndexError
    converter_dic = {"A":1, "B":2, "C":3, "D":4, "E":5, "F":6, "G":7, "H":8, "I":9, "J":10, "K":11, "L":12, "M":13, "N":14, "O":15, "P":16, "Q":17, "R":18, "S":19, "T":20, "U":21, "V":22, "W":23, "X":24, "Y":25, "Z":26, " ":27,
                     1:"A", 2:"B", 3:"C", 4:"D", 5:"E", 6:"F", 7:"G", 8:"H", 9:"I", 10:"J", 11:"K", 12:"L", 13:"M", 14:"N", 15:"O", 16:"P", 17:"Q", 18:"R", 19:"S", 20:"T", 21:"U", 22:"V", 23:"W", 24:"X", 25:"Y", 26:"Z", 27:" "}
    

    def find_determinant(key_matrix):
        determinant = 0
        if len(key_matrix) == 2:
            return key_matrix[0][0] * key_matrix[1][1] - key_matrix[0][1] * key_matrix[1][0]
        for j in range(len(key_matrix[0])):
            sub_matrix = [[key_matrix[l][k] for k in range(len(key_matrix[l])) if k != j] for l in range(1,len(key_matrix))]
            determinant += ((-1)**j) * key_matrix[0][j] * find_determinant(sub_matrix)
        return determinant
    

    def adjugate(key_matrix):
        adjugate_matrix = [i.copy() for i in key_matrix]
        if len(key_matrix) == 2:
            return [[key_matrix[1][1],-key_matrix[0][1]],[-key_matrix[1][0],key_matrix[0][0]]]
        for i in range(len(key_matrix)):
            for j in range(len(key_matrix[i])):
                sub_matrix = [[key_matrix[l][k] for k in range(len(key_matrix[l])) if k != j] for l in range(len(key_matrix)) if i != l]
                adjugate_matrix[j][i] = ((-1) ** (j+i)) * find_determinant(sub_matrix)
        return adjugate_matrix
    
    
    def multiply_matrix(message,key):
        new_matrix = [[0 for i in range(len(message[0]))] for i in range(len(message))]
        for k in range(len(message)):
            for i in range(len(key)):
                for j in range(len(key[i])):
                    new_matrix[k][i] += key[i][j] * message[k][j] 
        return new_matrix
    
    
    def decrypt(message,key):
        det = 1/find_determinant(key)
        adjugat = adjugate(key)
        inverse_matrix = [[int(det * i) for i in j] for j in adjugat]
        return multiply_matrix(message,inverse_matrix)


    if sys.argv[2][-3:] != "txt":
        print("Key file could not be read error")
        quit()
    if sys.argv[3][-3:] != "txt":
        print("The input file could not be read error")
        quit()
    with open(sys.argv[2],"r") as f:
        key = [list(map(int,i.rstrip("\n").split(","))) for i in f.readlines()]
        if not key:
                raise Empty(sys.argv[2])
    if sys.argv[1] == "enc":
        with open(sys.argv[3],"r") as f:
            message = [converter_dic[i.upper()] for i in f.readline()]
            if not message:
                raise Empty(sys.argv[3])
        if len(message) % len(key) != 0:
            message.extend([27 for i in range(len(key) - (len(message) % len(key)))])
        message = [message[i:i+len(key)] for i in range(0,len(message),len(key))]
        encode = multiply_matrix(message,key)
        with open(sys.argv[4],"w") as f:
            f.write(",".join([str(encode[i][j]) for i in range(len(encode)) for j in range(len(encode[i]))]))
    elif sys.argv[1] == "dec":
        with open(sys.argv[3],"r") as f:
            message = list(map(int,f.readline().split(",")))
        message = [message[i:i+len(key)] for i in range(0,len(message),len(key))]
        decode = decrypt(message,key)
        with open(sys.argv[4],"w") as f: 
            f.write("".join([converter_dic[int(j)] for i in range(len(decode)) for j in decode[i]]))
    else:
        print("Undefined parameter error")
except IndexError:
    print("Parameter number error")
except FileNotFoundError as a:
    if a.filename == sys.argv[3]:
        print("Input file not found error")
    elif a.filename == sys.argv[2]:
        print("Key file not found error")
except Empty as a:
    if a.argument == sys.argv[3]:
        print("Input file is empty error")
    elif a.argument == sys.argv[2]:
        print("Key file is empty error")
except KeyError:
    print("Invalid character in input file error")
except ValueError:
    print("Invalid character in key file error")
