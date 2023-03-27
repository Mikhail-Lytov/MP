import collections
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


def Knuth_Morris_Pratt(line = "", image = ""):
    image_list = []
    dict = {}
    size = 0
    size_list = []
    for i in image:
        if dict.get(i) is None:
            dict[i] = size;
            image_list.append(i)
        else:
            image_list.append(i)
            left_image = []
            right_image = []
            size_image = 1;
            for j in image_list[0:len(image_list) - 1]:
                left_image.append(j)
                right_image.insert(0, image_list[len(image_list)-size_image])
                print(left_image)
                print(right_image)
                size_image += 1
                if(left_image == right_image):
                    size += 1
            if(size != 0):
                dict[i] = size
            size = 0
    return

if __name__ == '__main__':
    print(Knuth_Morris_Pratt(line="персональные данные", image="abcabd"))