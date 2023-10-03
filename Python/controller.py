import view
import model
import text


def notes():

    notes = model.Notes()
    notes.read_file_json()
    
    while True:
        user_select = view.show_menu()
        match user_select:
            case 1:
                notes.print_notes(notes.note_book)
            case 2:
                notes.filter_notes_by_date()
            case 3: 
                notes.print_notes_id()
            case 4: 
                notes.add_note()
            case 5: 
                notes.edit_note()
            case 6: 
                notes.delete_note()
            case 7:
                print(text.main_menu_exit)
                break
