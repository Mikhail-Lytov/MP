def BoyerMoore(line = "", image = ""):
    remotenessList = []
    dict = {}
    size = 1
    flag = True
    occurrencesList = []
    for i in image[len(image) - 2::-1]:
        if(dict.get(i) == None):
            dict[i] = size
            size += 1
        else: size += 1
    if(dict.get(image[len(image)-1]) == None):
        dict[image[len(image) - 1]] = size
    size = len(image) - 1
    i = size
    while i < len(line):
        if line[i] != image[size]:
            try:
                i += dict.get(line[i])
            except TypeError:
                i += size
        else:
            while size > 0:
                i -= 1
                if line[i] == image[size - 1]:
                    size -= 1
                else:
                    flag = False
                    size = len(image) - 1
                    try:
                        i += dict.get(line[i])
                    except TypeError:
                        i += size
                    break
            if flag:
                size = len(image)-1
                occurrencesList.append(i)
                i += size + 1
    return occurrencesList
if __name__ == '__main__':
    print(BoyerMoore(line="персональные данные", image="данные"))