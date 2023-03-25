import collections
import substring

def test():
    dictList = [13]
    if collections.Counter(substring.BoyerMoore(line="персональные данные", image="данные")) == collections.Counter(dictList):
        print("BoyerMoore: " + str(True))




if __name__ == '__main__':
    test()
