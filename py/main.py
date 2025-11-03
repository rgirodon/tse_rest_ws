from fastapi import FastAPI
from pydantic import BaseModel

class Team(BaseModel):
    id: int
    name: str

app = FastAPI()

data = {
    'teams': [
        Team(id=1, name='Paris SG'),
        Team(id=2, name='Olympique Lyonnais'),
        Team(id=3, name='Olympique Marseille'),
        Team(id=4, name='AS Monaco'),
        Team(id=5, name='RC Lens'),
        Team(id=6, name='Lille Olympique SC'),
        Team(id=7, name='RC Strasbourg')]
}

@app.get("/teams")
def get_teams():
    # renvoyer nos données et 200 code OK
    return data["teams"]

@app.get("/teams/{id}")
def get_team(id: int):

    result = {'message': "Team does not exist"}

    for team in data["teams"]:
        if (team.id == id):
            result = team 

    return result


@app.post("/teams")
def post_team(team: Team):

    data['teams'].append(team)

    # réponse de retour
    return {'message': "Team added"}


@app.delete("/teams/{id}")
def delete_team(id: int):

    team_to_delete = False

    for team in data["teams"]:
        if (team.id == id):
            team_to_delete = team 

    # si la team est présente, supprimez-la
    if (team_to_delete):
        data['teams'].remove(team_to_delete)

        # réponse de retour confirmant la suppression
        return {'message':'Team deleted'}
    
    # si la team n'est pas présente, renvoyez simplement la réponse
    else:
        return {'message': "Team does not exist"}


@app.put("/teams/{id}")
def update_team(id: int, team: Team):

    team_updated = False

    for current_team in data["teams"]:
        if (current_team.id == id):

            current_team.name = team.name

            team_updated = True 

    # si la team est présente, supprimez-la
    if (team_updated):

        # réponse de retour confirmant la suppression
        return {'message':'Team updated'}
    
    # si la team n'est pas présente, renvoyez simplement la réponse
    else:
        return {'message': "Team does not exist"}

