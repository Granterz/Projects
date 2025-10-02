# Assignment 2 - Coursework 2
# Due for 12/01/24
# Author - Josh Grant - 60042385

import os  # Imports the module into the program


class VotingSystem:
    def __init__(self, polling_station_name):
        while not polling_station_name or polling_station_name.isspace():
            print("Error: Polling station name cannot be blank.")
            polling_station_name = input("Enter the name of the polling station: ").strip()

        self.polling_station_name = polling_station_name
        self.file_path = f"{polling_station_name}.txt"  # this produces a text file
        self.password = self.polling_station_name  # Secret password for exiting the polling booth menu
        self.candidates = {
            "Blue Party - Bert Navy": 0,
            "Green Party - Luke Lime": 0,
            "Orange Party - Sally Tangerine": 0,
            "Red Party - Rose Burgundy": 0,
            "Yellow Party - Edward Yoke": 0,
        }
        self.male_tally = 0
        self.female_tally = 0
        self.voting_enabled = False  # Flag to indicate whether voting is enabled

    def setup_polling_station_file(self):
        # Initialize the candidates and gender tallies to zero
        self.candidates = {candidate: 0 for candidate in self.candidates}
        self.male_tally = 0
        self.female_tally = 0
        self.voting_enabled = True
        print(f"Polling station votes file set up for {self.polling_station_name}.")

    def enter_polling_booth(self):
        if not self.voting_enabled:
            print("Please set up the polling station votes file first.")
            return  # the main menu cannot be accessed until the polling station votes file has been set up

        while True:  # displays polling booth menu
            print("\nPolling Booth Menu")
            print("***********************")
            print("1. Display Candidates")
            print("2. Display Gender Tally")
            print("3. Vote")
            print("4. Exit Polling Booth")

            choice = input("Enter your choice: ")

            if choice == "1":
                self.display_candidates()
            elif choice == "2":
                self.display_gender_tally()
            elif choice == "3":
                self.vote_process()
            elif choice == "4":
                exit_password = input("Enter the exit password: ")
                if exit_password == self.password:  # password is the name of the polling station file
                    print("Exiting Polling Booth.")
                    break
                else:
                    print("Incorrect exit password. Access denied.")
            else:
                print("Invalid choice. Please enter a valid option.")

    def vote_process(self):
        while True:
            gender = input("Enter your gender (Male/Female): ").lower()
            if gender in ["male", "female"]:  # only 'male' and 'female' will be accepted as valid inputs
                break
            else:
                print("Invalid gender input. Please enter Male or Female.")

        for candidate in self.candidates:
            while True:
                try:
                    vote_input = input(f"Enter your vote for {candidate} (0-5 or <CR>): ")
                    vote = float(vote_input) if vote_input else 0

                    if not (0 <= vote <= 5) or (vote % 1 != 0 and vote != 0):
                        print("Invalid vote value. Please enter a valid numeric value between 0 and 5 or <CR>.")
                        continue  # only 0-5 or 'enter key' will be accepted as valid inputs

                except ValueError:
                    print("Invalid input. Please enter a valid numeric value or <CR>.")
                    continue

                break

            self.candidates[
                candidate] += 1 if vote == 1 else 0.5 if vote == 2 else 0.33 if vote == 3 else 0.25 if vote == 4 \
                else 0.2

        if gender == "male":
            self.male_tally += 1
        else:
            self.female_tally += 1

        print("Vote recorded successfully.")  # voting data is saved and used to perform the calculations for
        # the statistics menu options

    def review_statistics(self):
        if not self.voting_enabled:
            print("Please set up the polling station votes file first.")
            return

        while True:  # displays statistics main menu
            print("\nReview Statistics - Votes Analysis")
            print("***************************************")
            print("1. Display votes tally (ordered by party name)")
            print("2. Display votes tally (ordered in descending order of votes)")
            print("3. Overall winner, with percentage share of the total votes")
            print("4. Percentage breakdown of male to female split")
            print("5. Return to main menu")

            choice = input("Enter your choice: ")

            if choice == "1":
                self.display_votes_tally_by_party()
            elif choice == "2":
                self.display_votes_tally_descending()
            elif choice == "3":
                self.display_overall_winner()
            elif choice == "4":
                self.display_gender_percentage()
            elif choice == "5":
                break
            else:
                print("Invalid choice. Please enter a valid option.")

    def display_candidates(self):  # displays candidates
        for candidate, votes in self.candidates.items():
            print(f"{candidate}: {votes} votes")

    def display_gender_tally(self):  # displays gender tally
        print(f"Male Tally: {self.male_tally} votes")
        print(f"Female Tally: {self.female_tally} votes")

    def display_votes_tally_by_party(self):  # displays votes tally by party
        sorted_candidates = sorted(self.candidates.items(), key=lambda x: x[0])  # Sort by party name
        for candidate, votes in sorted_candidates:
            print(f"{candidate}: {votes} votes")

    def display_votes_tally_descending(self):  # displays votes tally in a descending order
        sorted_candidates = sorted(self.candidates.items(), key=lambda x: x[1],
                                   reverse=True)  # Sort by votes descending
        for candidate, votes in sorted_candidates:
            print(f"{candidate}: {votes} votes")

    def display_overall_winner(self):  # displays the overall winner in terms of votes
        winner = max(self.candidates, key=self.candidates.get)
        total_votes = sum(self.candidates.values())
        percentage_share = (self.candidates[winner] / total_votes) * 100 if total_votes > 0 else 0
        print(f"Overall winner: {winner} with {percentage_share:.2f}% of the {total_votes} votes cast.")

    def display_gender_percentage(self):  # displays gender percentage
        total_votes = sum(self.candidates.values())
        male_percentage = (self.male_tally / total_votes) * 100 if total_votes > 0 else 0
        female_percentage = (self.female_tally / total_votes) * 100 if total_votes > 0 else 0
        print(f"Male percentage: {male_percentage:.2f}%")
        print(f"Female percentage: {female_percentage:.2f}%")

    def save_to_file(self):  # when you exit the program the name of your polling station is saved as a text file
        with open(self.file_path, "w") as file:
            for candidate, votes in self.candidates.items():
                file.write(f"{candidate}: {votes}\n")
            file.write(f"Male Tally: {self.male_tally}\n")
            file.write(f"Female Tally: {self.female_tally}\n")

    def load_from_file(self):  # you can open your previously saved text files to see the votes and their statistics
        if os.path.exists(self.file_path):
            with open(self.file_path, "r") as file:
                lines = file.readlines()
                for i in range(5):
                    candidate, votes = lines[i].strip().split(": ")
                    self.candidates[candidate] = int(votes)
                self.male_tally = int(lines[5].split(": ")[1])
                self.female_tally = int(lines[6].split(": ")[1])


if __name__ == "__main__":
    polling_station_name = input("Enter the name of the polling station: ")
    voting_system = VotingSystem(polling_station_name)

    # Load existing data if available
    voting_system.load_from_file()

    while True:  # displays main menu
        print("\nNI Electoral System")
        print("***********************")
        print("1. Setup polling station votes file")
        print("2. Enter polling booth")
        print("3. Review statistics")
        print("4. Exit")

        choice = input("Enter your choice: ")

        if choice == "1":
            voting_system.setup_polling_station_file()
        elif choice == "2":
            voting_system.enter_polling_booth()
        elif choice == "3":
            voting_system.review_statistics()
        elif choice == "4":
            voting_system.save_to_file()
            print(f"Voting data saved to {voting_system.file_path}")
            break
        else:
            print("Invalid choice. Please enter a valid option.")
