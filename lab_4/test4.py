from hash_map import HashMap


map = HashMap()


print("Добавим элемент 'hello' c ключом 'key1':")
map.set('key1', 'hello')
print(f"Проверим, что элемент добавился:")
print(f"map['key1']={map.get('key1')}")

print()

print("Добавим элемент False c ключом 'key2':")
map.set('key2', False)
print(f"Проверим, что элемент добавился:")
print(f"map['key2']={map.get('key2')}")

print()

print("Добавим элемент 12334 c ключом 'key3':")
map.set('key3', 12334)
print(f"Проверим, что элемент добавился:")
print(f"map['key3']={map.get('key3')}")

print()

print("Изменим значение элемента по ключу 'key2' c False на True:")
map.delete('key2')
map.set('key2', True)
print(f"Проверим, что элемент изменился:")
print(f"map['key2']={map.get('key2')}")


print()

print("Удалим элемент с ключом 'key3':")
map.delete('key3')
print(f"Проверим, что элемент удалился:")
print(f"map['key3']={map.get('key3')}")
