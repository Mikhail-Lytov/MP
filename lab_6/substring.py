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


# Функция для реализации алгоритма КМП
def Knuth_Morris_Pratt(text, pattern):

    list_pattern = []
    # нету элемента поиска
    if not pattern:
        print('пусто')
        return

    # элемент поиска больше искомого текста
    if not text or len(pattern) > len(text):
        print('элемент поиска больше искомого текста')
        return

    chars = list(pattern)

    # next[i] сохраняет индекс следующего лучшего частичного совпадения
    next = [0] * (len(pattern) + 1)

    for i in range(1, len(pattern)):
        j = next[i + 1]

        while j > 0 and chars[j] is not chars[i]:
            j = next[j]

        if j > 0 or chars[j] == chars[i]:
            next[i + 1] = j + 1

    j = 0
    for i in range(len(text)):
        if j < len(pattern) and text[i] == pattern[j]:
            j = j + 1
            if j == len(pattern):
                list_pattern.append(i - j + 1)
        elif j > 0:
            j = next[j]
            i = i - 1        #, так как `i` будет увеличен на следующей итерации
    return list_pattern

if __name__ == '__main__':
    print(Knuth_Morris_Pratt("ABCABAABCABAC", "CAB"))