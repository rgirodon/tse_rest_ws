from fastapi import FastAPI

app = FastAPI()

data = {
    'teams': [
        'Paris SG',
        'Olympique Lyonnais',
        'Olympique Marseille',
        'AS Monaco',
        'RC Lens',
        'Lille Olympique SC',
        'RC Strasbourg']
}

@app.get("/teams")
def get_teams():
    # renvoyer nos données et 200 code OK
    return data["teams"]

@app.get("/teams/{idx}")
def get_team(idx: int):
    # si la team est présente, supprimez-la
    if (idx <= (len(data['teams']) - 1)):

        # envoyer nos données et 200 code OK
        return data['teams'][idx]
    
    # si la team n'est pas présente, renvoyez simplement la réponse
    else:
        return {'message': "Team does not exist"}
    
@app.post("/teams")
def post_team(team: str):
    # si le team est déjà présent, nous ne l'ajoutons pas aux données
    if team in data['teams']:
        # donc retourner simplement la réponse avec un message disant qu'il existe déjà
        return {'message': "Team already exists"}
    
    # par contre si la team n'est pas présente, nous ajoutons la team aux données
    else:
        data['teams'].append(team)

        # réponse de retour
        return {'message': "Team added"}

@app.delete("/teams/{idx}")
def delete_team(idx: int):
    # si la team est présente, supprimez-la
    if (idx <= (len(data['teams']) - 1)):
        data['teams'].pop(idx)

        # réponse de retour confirmant la suppression
        return {'message':'Team deleted'}
    
    # si la team n'est pas présente, renvoyez simplement la réponse
    else:
        return {'message': "Team does not exist"}

@app.put("/teams/{idx}")
def update_team(idx: int, team: str):
    # si la team est présente, modifiez-la
    if (idx <= (len(data['teams']) - 1)):
        data['teams'][idx] = team

        # réponse de retour confirmant la suppression
        return {'message':'Team updated'}
    
    # si la team n'est pas présente, renvoyez simplement la réponse
    else:
        return {'message': "Team does not exist"}