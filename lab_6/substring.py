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


def prefix(line = "", image = ""):
    image_list = []
    dict = {}
    size = 0
    maxSize = 0
    for i in image:
        if dict.get(i) is None:
            dict[i] = size;
            image_list.append(i)
        else:
            image_list.append(i)
            left_image = []
            right_image = []
            list_image = []
            size_image = 1;
            for j in image_list[0:len(image_list) - 1]:
                left_image.append(j)
                right_image.insert(0, image_list[len(image_list)-size_image])
                print(left_image)
                print(right_image)
                size_image += 1
                if(left_image == right_image):
                    if(len(list_image) == 0):
                        list_image.append(''.join(left_image))
                        size = len(left_image)
                        if size > maxSize: maxSize = size
                    else:
                        list_image[0] = ''.join(left_image)
                        size = len(left_image)
            if(size != 0):
                dict[list_image[0]] = size
            size = 0
    return maxSize, dict

def Knuth_Morris_Pratt(line='', image=''):
    i = len(image) - 1
    str_image = ''
    flag = False
    maxSize, dict = prefix(line, image)
    while i < len(line):
        j = len(image) - 1
        if(image[j] == line[i] and j > -1):
            str_image = str(image[j]) + str_image
            j -= 1
            i -= 1
            flag = True
        elif j == 0:
            print("шей")
        elif flag:
            if(dict.get(str_image) != None):
                i += dict.get(str_image)
            else:
                i += len(image)-1
        else: i += len(image) - 1

if __name__ == '__main__':
    print(Knuth_Morris_Pratt(line="abcabeabcabcabd", image="abcabd"))