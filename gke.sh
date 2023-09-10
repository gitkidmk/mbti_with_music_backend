#!/bin/sh
export PROJECT_ID=verdant-option-398406
gcloud config set project $PROJECT_ID
docker build -t asia-docker.pkg.dev/${PROJECT_ID}/mbti-repo/mbti-be:v1 .
gcloud artifacts repositories add-iam-policy-binding mbti-repo \
    --location=asia \
    --member=serviceAccount:604574044484-compute@developer.gserviceaccount.com \
    --role="roles/artifactregistry.reader"

gcloud auth configure-docker asia-docker.pkg.dev
docker push asia-docker.pkg.dev/${PROJECT_ID}/mbti-repo/mbti-be:v1