import collections
import substring

def test():
    dictList = [13]
    if collections.Counter(substring.BoyerMoore(line="персональные данные", image="данные")) == collections.Counter(dictList):
        print("BoyerMoore: " + str(True))
    else:
        print("BoyerMoore: " + str(False))

    dictList = [2, 8]
    if substring.Knuth_Morris_Pratt("ABCABAABCABAC", "CAB") == dictList:
        print("Knuth_Morris_Pratt: " + str(True))
    else:
        print("Knuth_Morris_Pratt: " + str(False))




if __name__ == '__main__':
    test()
