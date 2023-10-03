import json
import datetime
import os
import text


class Notes: 


    def __init__(self, path: str ='./Python/notes.json'):

        self.note_book = []
        self.path = path
        

    def read_file_json(self):
        
        if not os.path.exists(self.path):
            with open(self.path, 'w'):
                pass
        try:
            with open(self.path, 'r', encoding ='utf8') as json_file:
                data = json.load(json_file)
                for note in data: 
                    self.note_book.append([note[0], note[1], note[2], note[3]])
            
        except json.decoder.JSONDecodeError:
            pass
    

    def sortNotes(self):

        self.note_book.sort(key= self.getKey)


    def setId(self):

        self.sortNotes()
        i = 0
        for note in self.note_book:
            i += 1                                                                                                        
            note[0] = i
        

    def getKey(self, note): 

        return note[3]


    def save_notes_json(self):

        with open(self.path, 'w', encoding ='utf8') as json_file:   
            json.dump(self.note_book, json_file, ensure_ascii = False)


    def filter_notes_by_date(self):

        if not self.note_book:
            print(text.notes_not_found)
        else:
            try:
                date_str = input(text.filter_date)
                date = datetime.datetime.strptime(date_str, '%Y-%m-%d').date()
                filtered_notes = []
                for note in self.note_book:
                    note_date = datetime.datetime.strptime(note[3], '%Y-%m-%d %H:%M:%S')
                    if note_date.date() == date:
                        filtered_notes.append(note)
                if not filtered_notes:
                    print(text.notes_date_not_found)
                else:
                    self.print_notes(filtered_notes)
                    
            except ValueError:
                print(text.date_error)


    def print_notes(self, note_book):

        if not note_book:
            print(text.notes_not_found)
        else:
             for note in note_book:
                print(text.section_field)
                print(f': {note[0]}; {text.title}: {note[1]}')
                print(f'{text.body}: {note[2]};') 
                print(f'{text.date}: {note[3]};')  
                    

    def print_note(self, note):

        print(text.section_field)
        print(f'id: {note[0]}; {text.title}: {note[1]};')
        print(f'{text.body}: {note[2]};') 
        print(f'{text.date}: {note[3]};') 


    def print_notes_id(self):

        if not self.note_book:
            print(text.notes_not_found)
        else:
            try:
                id_note = int(input(text.input_note_id))
                note = [note for note in self.note_book if note[0] == id_note]
                if not note:
                    print(text.id_note_not_found)
                else:
                    self.print_note(note[0])

            except ValueError:
                print(text.input_error)


    def add_note(self):

        id_note = len(self.note_book) + 1
        title = input(text.input_title)
        body = input(text.input_body)
        date = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        new_note = [id_note, title, body, date]
        
        self.note_book.append(new_note)
        self.save_notes_json()
        print(text.add_new_note_successful)


    def edit_note(self):

        if not self.note_book:
            print(text.notes_not_found)
        else:
            try:
                id_note = int(input(text.input_note_id))
                for note in self.note_book:
                    if id_note == note[0]:
                        new_title = input(f'{text.input_new_title}{note[1]}): ')
                        new_body = input(f'{text.input_new_body}{note[2]}): ')
                        if new_title:
                            note[1] = new_title
                            note[3] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
                            self.setId()
                        if new_body:
                            note[2] = new_body
                            note[3] = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
                            self.setId()
                        if not new_title and not new_body:
                            print(text.note_is_not_change)
                            break
                        self.save_notes_json()
                        print(text.edit_note_successful)
                        break
                    else:
                        print(text.id_note_not_found)

            except ValueError:
                print(text.input_error)


    def delete_note(self):

        if not self.note_book:
            print(text.notes_not_found)
        else:
            try:
                id_note = int(input(text.input_note_id))
                for note in self.note_book:
                    print(note[0])
                    if id_note == note[0]:
                        self.print_note(note)
                        print(text.section_field)
                        answer = int(input(text.ask_delete_note))
                        if answer == 1:
                            self.note_book.remove(note)
                            self.save_notes_json()
                            print(text.note_delete_successful)
                            self.setId()
                        else:
                            print(text.note_delete_abort)
                        break
                    else:
                        print(text.id_note_not_found)
                        
            except ValueError:
                print(text.input_error)
